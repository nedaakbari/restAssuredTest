//package ir.sample.one;
//
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import org.hamcrest.Matchers;
//import org.testng.annotations.Test;
//
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//import static org.hamcrest.Matchers.*;
//
//public class ValidatedResponseDemo {
//    static final String BASE_URL = "https://api.github.com";
//
//    @Test
//    void response() {
//        RestAssured.get(BASE_URL)
//                .then()
//                .statusCode(200)//inseted of assert.equal
//                .contentType(ContentType.JSON)
//                .header("x-ratelimit-limit", "60");
//    }
//
//
//    @Test
//    void response3() {
//        RestAssured.get(BASE_URL)
//                .then()
////                .statusCode(200)
//                .statusCode(lessThan(300))
//                .statusCode(anyOf(equalTo(200), equalTo(202)))
//                .header("x-ratelimit-limit", notNullValue())
//                .header("x-ratelimit-limit", Matchers.not(emptyString()))
//                .header("x-ratelimit-limit", notNullValue())
//                .header("x-ratelimit-limit", containsStringIgnoringCase("6"));
//    }
//
//    @Test
//    void response4() {
//        RestAssured.get(BASE_URL)
//                .then()
//                .statusCode(200)
//                .statusCode(lessThan(300))
//                .header("cache-control", containsStringIgnoringCase("max-age=60"))
//                .time(lessThan(3L), TimeUnit.SECONDS)
//                .header("etag", notNullValue())
////                .header("etags", is(emptyString()))
//                .header("etag", not(emptyString()));
//
//    }
//
//    Map<String, String> expectedHeaders = Map.of(
//            "content-encoding", "gzip",
//            "access-control-allow-origin", "*");
//
//    @Test
//    void response5() {
//        RestAssured.get(BASE_URL)
//                .then()
//
//                .headers("cache-control", containsStringIgnoringCase("public"),
//                        "content-encoding", "gzip",
//                        "access-control-allow-origin", "*")
//                .headers(expectedHeaders);
//
//    }
//}
