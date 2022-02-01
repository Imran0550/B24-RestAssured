package com.cybertek.tests.day04_ORDS_Path;

import com.cybertek.tests.ZippopTestBase;
import com.cybertek.tests.ZippopTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ZippopZipCodeTest extends ZippopTestBase {

    @Test
    public void zipopTest(){

        Response response = given().accept(ContentType.JSON)
                           .when().get("/us/22031");
        System.out.println(response.statusCode());
    }
}
