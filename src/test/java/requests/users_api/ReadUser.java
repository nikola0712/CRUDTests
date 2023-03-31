package requests.users_api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ReadUser {

    public Response readUsers(String bearer_token) {
        return
        given()
                .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .header("Authorization", "Bearer " + bearer_token)
                .when()
                .get("/users")
                .then().contentType(ContentType.JSON).extract().response();
    }

    public Response readUser(int userId, String bearer_token) {
       return
        given()
                .queryParam("userId", userId)
                .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .header("Authorization", "Bearer " + bearer_token)
                .when()
                .get("/users")
                .then().contentType(ContentType.JSON).extract().response();
    }
}