package requests.users_api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.user.User;

import static io.restassured.RestAssured.given;

public class CreateUser {
    public Response createUser(User user, String bearer_token) {
        return
                given()
                        .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                        .header("Authorization", "Bearer " + bearer_token)
                        .body(user)
                        .when()
                        .post("/users")
                        .then().contentType(ContentType.JSON).extract().response();
    }

    public Response createUser(User user, String pathId, String bearer_token) {
        return
                given()
                        .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                        .header("Authorization", "Bearer " + bearer_token)
                        .body(user)
                        .when()
                        .post(pathId);
    }

    public Response createUser(String body, String bearer_token) {
        return
                given()
                        .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                        .header("Authorization", "Bearer " + bearer_token)
                        .body(body)
                        .when()
                        .patch("/users/");
    }
}
