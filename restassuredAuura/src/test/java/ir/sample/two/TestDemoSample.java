package ir.sample.two;

import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("REST API Regression Testing using TestNG")
@Feature("Verify that the Get and POST API returns correctly")
public class TestDemoSample {

    @Test(description = "To get the details of post with id 1", priority = 1)
    @Story("GET Request with Valid post id")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Verify that the GET API returns correctly")
    public void verifyGetAPI() {
        // Given
        given()
                .filter(new AllureRestAssured()) // Set up the AllureRestAssured filter to display request and response information in the test report
                .baseUri("https://jsonplaceholder.typicode.com")
                .header("Content-Type", "application/json")
                // When
                .when()
                .get("/posts/1")
                // Then
                .then()
                .statusCode(200)
                // To verify correct value
                .body("userId", equalTo(1))
                .body("id", equalTo(1))
                .body("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"))
                .body("body", equalTo("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"));
    }

    @Test(description = "To create a new post", priority = 2)
    @Story("POST Request")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Verify that the post API returns correctly")
    public void verifyPostAPI() {        // Given
        given()
                .filter(new AllureRestAssured())
                // Set up the AllureRestAssured filter to display request and response information in the test report
                .baseUri("https://jsonplaceholder.typicode.com")
                .header("Content-Type", "application/json")
                // When
                .when()
                .body("{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1\n}")
                .post("/posts")
                // Then
                .then()
                .statusCode(201)
                // To verify correct value
                .body("userId", equalTo(1))
                .body("id", equalTo(101))
                .body("title", equalTo("foo"))
                .body("body", equalTo("bar"));
    }

//    @Test(description = "failed Test", priority = 1)
//    @Story("should be failed")
//    @Severity(SeverityLevel.NORMAL)
//    @Description("Test Description : i want to test")
//    public void FailedAPISample() {
//        given()
//                .filter(new AllureRestAssured())
//                .baseUri("https://jsonplaceholder.typicode.com")
//                .header("Content-Type", "application/json")
//                .when()
//                .get("/posts/1")
//                .then()
//                .statusCode(400);
//    }
}