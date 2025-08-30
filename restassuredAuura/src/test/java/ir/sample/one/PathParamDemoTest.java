package ir.sample.one;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.Map;

public class PathParamDemoTest {
    //https://api.github.com/repos/andrejs-ps/playwright-java-starter
//    https://api.github.com/repos/andrejs-ps/Getting-Started-With-TestNG
    //https://github.com/andrejs-ps

    static final String REPO_EP = "https://api.github.com/repos";

    @Test
    void Path() {
        RestAssured.get(REPO_EP + "/andrejs-ps/playwright-java-starter")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(915030415));
    }

    @Test
    void withParam() {
        RestAssured
                .given()
                .pathParam("user", "andrejs-ps")
                .pathParam("repo_name", "playwright-java-starter")
                .get(REPO_EP + "/{user}/{repo_name}")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(915030415));

        Map<String, String> map = Map.of("user", "andrejs-ps"
                , "repo_name", "playwright-java-starter");
        RestAssured
                .given()
                .pathParams(map)
                .get(REPO_EP + "/{user}/{repo_name}")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(915030415));
    }
}
