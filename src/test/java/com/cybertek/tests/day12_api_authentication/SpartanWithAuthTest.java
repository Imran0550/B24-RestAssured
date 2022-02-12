package com.cybertek.tests.day12_api_authentication;

import com.cybertek.tests.SpartanWith7000PortTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import sun.security.util.Password;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class SpartanWithAuthTest  extends SpartanWith7000PortTestBase {

   /** Username: admin
    Password: admin

    Given accept type is json
    and basic auth credentials are "admin" , "admin"
    When I send get request to "/api/spartans"
    Then status code is 200
    and content type is json
    And json response should have Spartan list
    **/

    @Test
    public void adminGetAllSpartans(){
        given().accept(ContentType.JSON)
                .and().auth().basic("admin","admin")
                .when().get("api/spartans").then().assertThat().statusCode(200)
        .and().contentType(ContentType.JSON)
        .and().body("id",isA(List.class)).log().all();

    }

    @Test
    public void noAuthGetSpartanNegativeTest(){
        given().accept(ContentType.JSON)
                .when().get("api/spartans")
                .then().assertThat().statusCode(401)
                .and().contentType(ContentType.JSON)
                .and().body("message",is("Unauthorized"));



    }
}
