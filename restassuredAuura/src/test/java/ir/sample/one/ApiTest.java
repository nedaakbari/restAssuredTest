package ir.sample.one;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTest {
    @Test
    @Description("یک نمونه تستی")
    public void testGetApi1() {
        Response response = RestAssured
                .given()
                .baseUri("https://google.com")
                .when()
                .get()
                .then()
                .extract().response();
                // اضافه کردن اطلاعات به گزارش Allure
        Allure.addAttachment("API Response", response.body().asString());

        // تایید وضعیت HTTP
        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test
    @Description("2یک نمونه تستی")
    public void testGetApi2() {
        Response response = RestAssured
                .given()
                .baseUri("https://google.com")
                .when()
                .get()
                .then()
                .extract().response();
        // اضافه کردن اطلاعات به گزارش Allure
        Allure.addAttachment("API Response", response.body().asString());

        // تایید وضعیت HTTP
        Assert.assertEquals(response.statusCode(), 200);
    }

}
