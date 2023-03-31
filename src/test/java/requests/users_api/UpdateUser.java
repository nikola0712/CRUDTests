package requests.users_api;

import io.restassured.response.Response;
import io.restassured.http.ContentType;
import models.user.User;

import static io.restassured.RestAssured.given;

public class UpdateUser {

    public Response updateUser(User user, int userId, String bearer_token) {
        return
        given()
                .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .header("Authorization", "Bearer " + bearer_token)
                .body(user)
                .when()
                .patch("/users/" + userId)
                .then().contentType(ContentType.JSON).extract().response();
    }
}

