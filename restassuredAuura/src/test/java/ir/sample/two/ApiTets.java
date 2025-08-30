package ir.sample.two;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class ApiTets {
    @Test
    void test1() {
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        int statusCode = response.getStatusCode();
        System.out.println(statusCode);

        Assert.assertEquals(statusCode, 200);
        ResponseBody body = response.getBody();
        System.out.println(body.asString());
//        System.out.println(body.prettyPrint());
        System.out.println("tim taken : " + response.getTime());

        String header = response.getHeader("content-type");
        System.out.println(header);
    }

    @Test
    void Test2() {
        given().
                get("https://reqres.in/api/users?page=2").
                then().
                statusCode(200);
    }

    //https://github.com/Sielei/spring-rest-assured //todo
    //    Test GET endpoint: Find All available Job posts
    @Test
    void shouldFindAllAvailableJobPosts() {
        given()
                .queryParam("page", 2)//todo
                .queryParam("size", 50)
                .when()
                .get("/api/v1/jobs")
                .then()
                .statusCode(200)
                .body("totalElements", equalTo(1000))
                .body("pageNumber", equalTo(2))
                .body("totalPages", equalTo(20))
                .body("isFirst", equalTo(false))
                .body("isLast", equalTo(false))
                .body("hasNext", equalTo(true))
                .body("hasPrevious", equalTo(true))
//                .body("lotto.lottoId", equalTo(5), "lotto.winners.winnerId", hasItems(23, 54))//todo
                .body("data.size()", equalTo(50));
    }

    //    Testing GET endpoint: Find Job post by ID
    @Test
    void shouldFindJobPostById() {
        when()
                .get("/api/v1/jobs/{jobId}", 10)
                .then()
                .statusCode(200)
                .body("id", equalTo(10))
                .body("jobTitle", equalTo("Speech Pathologist"))
                .body("datePosted", equalTo("2024-02-19"));
    }

    //    Testing PUT endpoint: Update a Job Post
    @Test
    void shouldUpdateJobPost() {
        given().contentType(ContentType.JSON)
                .body("""
                          {
                            "jobTitle": "Electrical Engineer",
                            "description": "XYZ inc. Is looking for an experienced Electrical Engineer.",
                            "jobType": "CONTRACTOR",
                            "datePosted": "2023-09-18",
                            "jobLink": "https://xyz.bamboohr.hr/p/22b177hg675-electrical-engineer"
                          }
                        """
                )
                .when()
                .put("/api/v1/jobs/{jobId}", 3)
                .then()
                .statusCode(204);
    }

    //    Testing DELETE endpoint: Delete a Job Post
    @Test
    void shouldDeleteJobPost() {
        when()
                .delete("/api/v1/jobs/{jobId}", 50)
                .then()
                .statusCode(204);

//        assertThatExceptionOfType(JobNotFoundException.class)
//                .isThrownBy(() -> jobService.findJobById(50L))
//                .withMessage("Job with id: 50 not found!");
    }
}
