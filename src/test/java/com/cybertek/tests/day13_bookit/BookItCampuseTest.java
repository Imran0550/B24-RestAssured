package com.cybertek.tests.day13_bookit;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.not;
import com.cybertek.tests.BookItTestBase;
import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class BookItCampuseTest extends BookItTestBase {

    @Test
    public void getAllCampuses(){

        String accessToken = getAccessToken(ConfigurationReader.getProperty("teacher_email"),
                ConfigurationReader.getProperty("teacher_password"));
        System.out.println("accessToken = " + accessToken);


        given().accept(ContentType.JSON)
                .and().header("Authorization",accessToken)
                .when().get("/api/campuses")
                .then().assertThat()
                .statusCode(200);
    }
}
