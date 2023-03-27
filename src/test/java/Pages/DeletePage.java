package Pages;
import Base.Base;

import static io.restassured.RestAssured.given;

public class DeletePage extends Base {

    public void deletePost(int postId) {
        given()
                .pathParam("postId", postId)
                .when()
                .delete("/posts/{postId}")
                .then()
                .statusCode(200)
                .log().all();
    }
}

