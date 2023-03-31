package Base;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

import java.io.IOException;
import java.util.Properties;

public class Base {

    @BeforeClass
    public static void setUp() throws IOException {
        Properties props = new Properties();
        props.load(Base.class.getClassLoader().getResourceAsStream("config.properties"));
        RestAssured.baseURI = props.getProperty("api.user.uri");
    }
}
