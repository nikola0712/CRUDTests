package tests;

import Base.Base;
import enums.Gender;
import enums.Status;
import enums.HttpStatusCodes;
import models.user.User;
import requests.users_api.CreateUser;
import requests.users_api.DeleteUser;
import requests.users_api.ReadUser;
import requests.users_api.UpdateUser;
import utility.UserGenerate;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class APITests extends Base {

    //Private permanent token created for purpose of testing APIs
    private static final String BEARER_TOKEN = "689258cad29aeb8b735abc125bafa925990d7f09d3d7343a65efddd6c72c576d";
    private CreateUser createUser;
    private ReadUser readUser;
    private UpdateUser updateUser;
    private DeleteUser deleteUser;
    private UserGenerate userGenerate;

    @Before
    public void setUpEndpoints() {
        createUser = new CreateUser();
        readUser = new ReadUser();
        updateUser = new UpdateUser();
        deleteUser = new DeleteUser();
        userGenerate = new UserGenerate();
    }

    @Test
    public void createNewUser() {

        //Create a new user
        User user = new User(userGenerate.generateNewName(), Gender.MALE,
                userGenerate.generateNewEmail(), Status.ACTIVE);

        Response response = createUser.createUser(user, BEARER_TOKEN);

        assertEquals(HttpStatusCodes.SUCCESSFULLY_CREATED.getHttpStatusCode(), response.statusCode());
        assertThat("Name is not correct", response.jsonPath().getString("name").equals(user.getName()));
        assertThat("Email is not correct", response.jsonPath().getString("email").equals(user.getEmail()));
        assertEquals(response.jsonPath().getString("gender").toUpperCase(), user.getGender().toString());
        assertEquals(response.jsonPath().getString("status").toUpperCase(), user.getStatus().toString());
    }
    @Test
    public void getUsers() {
        //Read users list
        Response response = readUser.readUsers(BEARER_TOKEN);

        assertEquals(HttpStatusCodes.SUCCESSFULLY_RETURN.getHttpStatusCode(), response.statusCode());
        assertThat("Users are not retrieved", !(response.jsonPath().getString("name[0]").isEmpty()));
    }
    @Test
    public void getUser() {

        String email = userGenerate.generateNewEmail();

        //create user and then check is email correctly displayed through get user api
        User user = new User(userGenerate.generateNewName(), Gender.MALE,
                email, Status.ACTIVE);

        Response response = createUser.createUser(user, BEARER_TOKEN);
        int userId = Integer.parseInt(response.jsonPath().getString("id"));

        assertEquals(HttpStatusCodes.SUCCESSFULLY_RETURN.getHttpStatusCode(), readUser.readUser(userId, BEARER_TOKEN).statusCode());
        assertThat("Retrieved email is not the same as created", response.jsonPath().getJsonObject("email").equals(email));
    }

    @Test
    public void updateUser() {
        //Update user
        User user = new User(userGenerate.generateNewName(), Gender.MALE,
                userGenerate.generateNewEmail(), Status.INACTIVE);

        Response response = createUser.createUser(user, BEARER_TOKEN);
        int userId = Integer.parseInt(response.jsonPath().getString("id"));

        assertEquals(HttpStatusCodes.SUCCESSFULLY_RETURN.getHttpStatusCode(), updateUser.updateUser(user, userId, BEARER_TOKEN).statusCode());
        assertThat("Status is not correct", response.jsonPath().getString("status").equals("inactive"));
    }

    @Test
    public void deleteUser() {

        User user = new User(userGenerate.generateNewName(), Gender.MALE,
                userGenerate.generateNewEmail(), Status.ACTIVE);

        Response response = createUser.createUser(user, BEARER_TOKEN);
        int userId = Integer.parseInt(response.jsonPath().getString("id"));

        //Delete created user and assert status code
        assertEquals(HttpStatusCodes.SUCCESS_NO_CONTENT.getHttpStatusCode(), deleteUser.deleteUser(userId, BEARER_TOKEN).statusCode());
    }

    @Test
    public void requestNotFound() {

        User user = new User(userGenerate.generateNewName(), Gender.FEMALE,
                userGenerate.generateNewEmail(), Status.ACTIVE);

        Response response = createUser.createUser(user, "wrongEndpointUri", BEARER_TOKEN);

        assertEquals(HttpStatusCodes.NOT_FOUND.getHttpStatusCode(), response.statusCode());
    }

    @Test
    public void badRequest() {

        String badJson = "{{\"name\":\"test\", \"email\":\"test@testmail.com\", \"status\":\"active\"}";

        Response response = createUser.createUser(badJson, BEARER_TOKEN);

        assertEquals(HttpStatusCodes.BAD_REQUEST.getHttpStatusCode(), response.statusCode());
    }
    @Test
    public void requestUnauthorized() {

        User user = new User(userGenerate.generateNewName(), Gender.MALE,
                userGenerate.generateNewEmail(),  Status.ACTIVE);

        Response response = createUser.createUser(user, "test-token");

        assertEquals(HttpStatusCodes.UNAUTHORIZED_ERROR.getHttpStatusCode(), response.statusCode());
    }
}