//package ir.sample;
//
//import io.qameta.allure.*;
//import io.qameta.allure.restassured.AllureRestAssured;
//import org.testng.annotations.Test;
//
//import static io.restassured.RestAssured.given;
//
//@Epic("REST APIs sample2")
//@Feature("API Fails2 ")
//public class TestSample {
//
//    @Test(description = "failed Test2", priority = 1)
//    @Story("should be failed2")
//    @Severity(SeverityLevel.NORMAL)
//    @Description("Test Description : i want to test2")
//    public void FailedAPISample1() {
//        given()
//                .filter(new AllureRestAssured())
//                .baseUri("https://jsonplaceholder.typicode.com")
//                .header("Content-Type", "application/json")
//                .when()
//                .get("/posts/1")
//                .then()
//                .statusCode(200);
//    }
//
//    @Test(description = "failed Test2", priority = 1)
//    @Story("should be failed2")
//    @Severity(SeverityLevel.NORMAL)
//    @Description("Test Description : i want to test2")
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
//
//}