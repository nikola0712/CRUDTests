package Pages;

import Base.Base;
import static io.restassured.RestAssured.given;

public class ReadPage extends Base {

    public void readPost(int postId) {
        given()
                .pathParam("postId", postId)
                .when()
                .get("/posts/{postId}")
                .then()
                .statusCode(200)
                .log().all();
    }
}
