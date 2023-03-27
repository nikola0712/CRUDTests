package Pages;

import Base.Base;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

public class UpdatePage extends Base {

    public void updatePostTitle(int postId, String title) {
        String updatedTitle = "{\n" +
                "  \"title\": \"" + title + "\"\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .pathParam("postId", postId)
                .body(updatedTitle)
                .when()
                .patch("/posts/{postId}")
                .then()
                .statusCode(200)
                .log().all();
    }
}

