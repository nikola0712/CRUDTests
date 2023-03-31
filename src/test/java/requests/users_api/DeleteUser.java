package requests.users_api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteUser {

    public Response deleteUser(int userId, String bearer_token) {
        return
                given()
                        .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                        .header("Authorization", "Bearer " + bearer_token)
                        .when()
                        .delete("/users/" + userId);
    }
}

