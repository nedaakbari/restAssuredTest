package ir.sample.one;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class QueryParamDemo {

    static final String SEARCH_EP = "https://api.github.com/search/repositories";

    //https://api.github.com/search/repositories?q=java
    //https://api.github.com/search/repositories?q=java&per_page=1
    @Test
    void withoutParam() {
        RestAssured.get(SEARCH_EP + "?q=java")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void withoutParam2() {
        RestAssured.get(SEARCH_EP + "?q=java&per_page=1")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void withParam() {
        RestAssured.
                given()
                .params(Map.of("q", "java",
                        "per_page", "1"))
                .get(SEARCH_EP)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    //حالا اگر مقدار فقط جاوا نبود چی؟ چندین مقدار بخوایم
    @DataProvider
    public Object[][] queryParams() {
        return new Object[][]{
                {Map.of("q", "java", "per_page", "1"), 1},
                {Map.of("q", "python", "per_page", "2"), 2}
        };
    }

    @Test(dataProvider = "queryParams")
    void dataDrivenTest(Map<String, Object> params, int expectedREpoCount) {
        JsonPath jsonPath =
                RestAssured.given()
                        .params(params)
                        .get(SEARCH_EP)
                        .prettyPeek()
                        .jsonPath();
        Assert.assertEquals(jsonPath.getInt("items.size()"), expectedREpoCount);
    }
}
