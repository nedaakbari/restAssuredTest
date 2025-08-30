package ir.sample.two;

import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Epic("REST APIs sample")
@Feature("API Fails ")
public class TestDemo {

    @Test(description = "failed Test", priority = 1)
    @Story("should be failed")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : i want to test")
    public void FailedAPISample() {
        given()
                .filter(new AllureRestAssured())
                .baseUri("https://jsonplaceholder.typicode.com")
                .header("Content-Type", "application/json")
                .when()
                .get("/posts/1")
                .then()
                .statusCode(400);
    }

}