package ir.sample.one;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.assertEquals;

public class BasicREsponseBodyDemo {
    //
    static final String LIMIT_EP = "https://api.github.com/rate_limit";

    @Test
    void Path() {
        Response response = RestAssured.get(LIMIT_EP);
        ResponseBody<?> body = response.getBody();
        JsonPath jsonPath = body.jsonPath();

        Map<String, String> resources = jsonPath.get("resources");
        Map<String, String> subMap2 = jsonPath.get("resources.core");
        Map<String, String> isNull = jsonPath.get("incorrect.path");   //NPE
//        int aMap = jsonPath.get("response.code");                      //ClassCastException
        String value3 = jsonPath.get("response.code.limit");           //ClassCastException


        int value = jsonPath.get("resources.core.limit");
        int value2 = jsonPath.get("resources.graphql.remaining");
        assertEquals(value2, 0);
        assertEquals(value, 60);
    }
}
