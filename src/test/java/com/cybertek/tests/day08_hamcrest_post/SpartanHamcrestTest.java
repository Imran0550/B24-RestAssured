package com.cybertek.tests.day08_hamcrest_post;
import com.cybertek.tests.SpartanTestBase;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;

public class SpartanHamcrestTest extends SpartanTestBase {

    @Test
    public void singleSpartanTest(){

        given().accept(ContentType.JSON)
                .when().get("api/spartans/24")
                .then().assertThat().statusCode(200)
        .and().assertThat().contentType(ContentType.JSON.toString())
        .body("id",is(equalTo(24)),"name",is(equalTo("Julio")))
        .and().header("Connection",is("keep-alive"))
        .log().all();
    }

    @Test
    public void getMapTest(){
       Map<String,Object> spartanMap = given().accept(ContentType.JSON)
                .when().get("api/spartans/24")
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .and().contentType(ContentType.JSON.toString())
                .and().extract().body().as(Map.class);

        System.out.println("spartanMap = " + spartanMap);
        assertThat(spartanMap.get("id"),is(24));
        assertThat(spartanMap.keySet(),containsInAnyOrder("id","name","gender","phone"));
        assertThat(spartanMap.values(),hasItems(24,"Julio","Male",9393139934L));
    }

    /**
     * given accept type json
     * get request to api/spartans
     * then status code is 200
     * and content type is json
     * then extract names of spartans into a List<String>
     */

    @Test
    public void getSpartanNamesTests(){
      List<String> names = given().accept(ContentType.JSON)
                .when().get("api/spartans")
                .then().assertThat().statusCode(200)
        .and().contentType(ContentType.JSON.toString())
        .and().extract().body().jsonPath().getList("name");

        System.out.println("names = " + names);
        assertThat(names,hasSize(111));
        assertThat(names,hasItems("Meade","Nels","Nels","Paige"));
    }

    @Test
    public void getTotalElementTest(){
        given().accept(ContentType.JSON)
                .and().queryParam("nameContains","v")
                .and().queryParam("gender","Male")
                .when().get("api/spartans/search")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
        .and().extract().path("totalElement");

    }

    @Test
    public void invalidSpartanTest(){
        given().accept(ContentType.JSON)
                .when().get("api/spartans/2400")
                .then().assertThat().statusCode(404)
                .body("error",equalTo("Not Found"));
    }
}
