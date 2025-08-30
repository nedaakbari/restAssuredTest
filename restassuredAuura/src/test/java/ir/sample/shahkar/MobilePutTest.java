package ir.sample.shahkar;

//import com.google.gson.Gson;
//import helper.RequestBody;
//import helper.ShahkarRules;
//import helper.randomValues.ShahkarHelper;
import io.restassured.http.ContentType;
import ir.sample.helper.RequestBody;
import ir.sample.helper.ShahkarRules;
import ir.sample.helper.randomValues.ShahkarHelper;
import org.testng.annotations.Test;

import java.sql.SQLException;

//import static helper.randomValues.ShahkarHelper.generateRequestId;
//import static helper.randomValues.ShahkarHelper.mobile;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class MobilePutTest {

    private static String baseUrl = "http://localhost:8088/rest/shahkar";
    private static String putUrl = "/put";
    private static String authToken = "Basic TWNpOm1jaTEyMzQ1Njc4";
    private static final String username = "Mci";
    private static final String password = "mci12345678";

    private static final int iranianPutMobile = ShahkarRules.iranianMobileNumber;

    @Test
    void iranianMobilePut() throws SQLException {
        int counter = 0;

        RequestBody iranianPerson = ShahkarHelper.getIranianPerson();
        while (counter < iranianPutMobile + 1) {
            iranianPerson.setRequestId(generateRequestId("0110"));
            iranianPerson.setService(mobile());
            var response = given()
//                .auth().basic(username, password)
                    .contentType(ContentType.JSON)//"application/json"
                    .header("Authorization", authToken)
                    .body(new Gson().toJson(iranianPerson))
//                    .log().all()
                    .when()
                    .post(baseUrl + putUrl);
            System.out.println("body: " + response.asString());
//            ShahkarResponseBody body = response.getBody().as(ShahkarResponseBody.class);

            System.out.println("response: " + response);
            System.out.println("statusCode: " + response.getStatusCode());
//            System.out.println("headers: " + response.getHeaders());

            if (response.getStatusCode() == 615 || response.getStatusCode() == 610) {
                continue;
            }

            if (counter < iranianPutMobile) {
                response.then()
                        .statusCode(200)
                        .body("response", equalTo(200))
                        .body("id", notNullValue())
                        .body("result", equalTo("OK."))
                        .body("requestId", equalTo(iranianPerson.getRequestId()))
                        .header("Content-Type", equalTo("application/json"));
            } else {
                response.then()
                        .statusCode(200)
                        .body("response", equalTo(601))
                        .body("$", hasKey("id"))
                        .body("id", nullValue())
                        .body("requestId", equalTo(iranianPerson.getRequestId()))
                        .header("Content-Type", equalTo("application/json"))
                        .body("result", equalTo("MaxAllowedNumberOfService;"));
            }
            counter++;
        }
    }
}
