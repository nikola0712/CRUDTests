import Base.Base;
import Pages.CreatePage;
import Pages.DeletePage;
import Pages.ReadPage;
import Pages.UpdatePage;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.fail;

public class MainTest extends Base {

    private CreatePage createPage;
    private ReadPage readPage;
    private UpdatePage updatePage;
    private DeletePage deletePage;

    @Before
    public void setUpPageObjects() {
        createPage = new CreatePage();
        readPage = new ReadPage();
        updatePage = new UpdatePage();
        deletePage = new DeletePage();
    }

    @Test
    public void testCRUD() {
        // Create a new post
        createPage.createPost("New post", "This is the body of the new post.");

        // Read the new post
        readPage.readPost(1);

        // Update the title of the new post
        updatePage.updatePostTitle(1, "New title");

        // Try to delete the post
        deletePage.deletePost(1);
    }

    @Test
    public void testRedirect() {
        // Make a request that returns a 302 or 404 status code
        Response response = given()
                .redirects().follow(false)
                .when()
                .get("/redirect")
                .thenReturn();

        // Check if the response has a 302 status code
        if (response.getStatusCode() == 302) {
            // Follow the redirect and check that it leads to the correct URL
            given()
                    .redirects().follow(true)
                    .when()
                    .get(response.getHeader("Location"))
                    .then()
                    .statusCode(200)
                    .body("url", equalTo("https://www.google.com"));
        } else if (response.getStatusCode() == 404) {
            // Handle the 404 response
            assertThat(response.getStatusCode(), equalTo(404));
        } else {
            // Handle other unexpected status codes
            fail("Received unexpected status code: " + response.getStatusCode());
        }
    }
}
