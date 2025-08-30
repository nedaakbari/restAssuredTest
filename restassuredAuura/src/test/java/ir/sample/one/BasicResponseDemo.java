package ir.sample.one;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicResponseDemo {
    static final String BASE_URL = "https://api.github.com";

    @Test
    void response() {
        Response response = RestAssured.get(BASE_URL);
        ResponseBody body = response.getBody();
        int statusCode = response.getStatusCode();
        String contentType = response.getContentType();

        Assert.assertEquals(statusCode,200);
        Assert.assertEquals(contentType,"application/json; charset=utf-8");
    }


    @Test
    void genericHeader() {
        Response response = RestAssured.get(BASE_URL);
        String server = response.getHeader("server");
        String limit = response.getHeader("x-ratelimit-limit");

        Assert.assertEquals(server,"github.com");
        Assert.assertEquals(limit,"60");
        Assert.assertEquals(Integer.parseInt(limit),60);
    }

    @Test
    void responseWithChain() {
        RestAssured.get(BASE_URL)
                .then()
                .statusCode(200)//inseted of assert.equal
                .contentType(ContentType.JSON)
                .header("x-ratelimit-limit","60");
    }

}
