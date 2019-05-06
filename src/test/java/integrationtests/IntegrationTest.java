package integrationtests;

import org.junit.BeforeClass;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.parsing.Parser;
import java.net.MalformedURLException;
import javax.servlet.ServletException;
import org.apache.catalina.LifecycleException;
import org.junit.AfterClass;
import org.junit.Test;
import testutils.EmbeddedTomcat;
import testutils.TestUtils;
import utils.PuSelector;

public class IntegrationTest {

    protected static int SERVER_PORT;
    protected static String APP_CONTEXT;
    protected static String SERVER_URL;

    public IntegrationTest() {
    }

    private static EmbeddedTomcat tomcat;

    @BeforeClass
    public static void setUpBeforeAll() throws ServletException, MalformedURLException, LifecycleException {
        System.out.println("INTEGRATION TEST");
        TestUtils.setupTestUsers(PuSelector.getEntityManagerFactory("pu_integration_test"));
        SERVER_PORT = 7777;
        APP_CONTEXT = "/jwtbackend";
        SERVER_URL = "http://localhost";
        //Setup and start Embedded Tomcat for testing
        tomcat = new EmbeddedTomcat();
        tomcat.start(SERVER_PORT, APP_CONTEXT);
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.basePath = APP_CONTEXT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterClass
    public static void tearDownAfterAll() {
        tomcat.stop();
    }

    @Test
    public void serverIsRunning() {
        System.out.println("Testing is server UP");
        given().when().get("/").then().statusCode(200);
    }

    @Test
    public void restTestEndPoint() {
        given().contentType("application/json").when().get("/api/car/test").then().statusCode(200);
    }

    @Test
    public void restAllEndPoint() {
        given().contentType("application/json").when().get("/api/car/all").then().statusCode(200);
    }

    @Test
    public void restAvailableEndPoint() {
        given().contentType("application/json").when().get("/api/car/available/04-05-2019/13-05-2019").then().statusCode(200);
    }

    @Test
    public void restCarEndPoint() {
        given().contentType("application/json").when().get("/api/car/AA12345").then().statusCode(200);
    }
}
