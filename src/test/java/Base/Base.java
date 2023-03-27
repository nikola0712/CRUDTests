package Base;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.AfterClass;

public class Base {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @AfterClass
    public static void tearDown() {
        // Clean up any test data that was created during the tests
    }
}
