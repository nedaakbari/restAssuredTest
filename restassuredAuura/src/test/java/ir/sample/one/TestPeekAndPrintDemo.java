package ir.sample.one;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
@Epic("REST APIs sample fot log")
@Feature("API log ")
public class TestPeekAndPrintDemo {
    static final String BASE_URL = "https://api.github.com";

    @Test(description = "log Test", priority = 1)
    @Story("log")
    @Severity(SeverityLevel.NORMAL)
    @Description("log test")
    void peekSample() {
        RestAssured.get(BASE_URL)
                .peek();//print header and body of response
    }

    @Test
    void prettyPeekSample() {
        RestAssured.get(BASE_URL)
                .prettyPeek();
    }

    @Test
    void print() {
        RestAssured.get(BASE_URL)
                .print();//print body of response
    }

    @Test
    void printPeekSample() {
        RestAssured.get(BASE_URL)
                .prettyPrint();
    }


    @Test
    void logSample() {

    }
}
