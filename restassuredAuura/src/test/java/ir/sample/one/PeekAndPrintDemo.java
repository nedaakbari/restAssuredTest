package ir.sample.one;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class PeekAndPrintDemo {
    static final String BASE_URL = "https://api.github.com";

    @Test
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
