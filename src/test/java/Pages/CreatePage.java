package Pages;

import Base.Base;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class CreatePage extends Base {

    public void createPost(String title, String body) {
        String postBody = "{\n" +
                "  \"userId\": 1,\n" +
                "  \"title\": \"" + title + "\",\n" +
                "  \"body\": \"" + body + "\"\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(postBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().all();
    }
}
