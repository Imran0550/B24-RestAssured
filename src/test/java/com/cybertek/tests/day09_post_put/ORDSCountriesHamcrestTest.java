package com.cybertek.tests.day09_post_put;

import com.cybertek.tests.ORDSTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ORDSCountriesHamcrestTest extends ORDSTestBase {


    @Test
    public void allCountriesHamcrestTest(){
        given().accept(ContentType.JSON)
                .when().get("/countries")
                .then().assertThat().statusCode(200)
         .and().contentType(ContentType.JSON)
         .and().body("count",equalTo(25),
                "items.country_id",hasItems("AR","AU","BE","BR","CA"),
                "items.country_name",hasItems("Argentina","Australia","Belgium","Brazil"))
        .log().all();


        Response response = given().accept(ContentType.JSON)
                .when().get("/countries");

        assertThat(response.statusCode(),equalTo(200));
        assertThat(response.contentType(),is("application/json"));
        assertThat(response.path("count"),is(25));
        assertThat(response.path("items.country_id"),hasItems("AR","AU","BE","BR","CA"));
        assertThat(response.path("items.country_name"),hasItems("Argentina","Australia","Belgium","Brazil","Canada"));
    }
}
