package com.cybertek.tests.day04_ORDS_Path;
import com.cybertek.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSGetRequestsTest {


    @BeforeAll
    public static void setUp(){
        baseURI = ConfigurationReader.getProperty("ords.url");
    }

    /**
     * Given accept type is json
     * When user send get request to /ords/hr/regions
     * Status code should be 200
     * Content type should be "application/json"
     * And body should contain "Europe"
     */

    @DisplayName("Get Request to /ords/hr/regions")
    @Test
    public void getAllRegionsTest() {


       Response response = given().accept(ContentType.JSON)
                          .when().get("/regions");

        System.out.println(response.statusCode());

        Assertions.assertEquals(200,response.statusCode());

        System.out.println("Content type = " + response.contentType());
        Assertions.assertEquals("application/json",response.contentType());

        response.prettyPrint();

        Assertions.assertTrue(response.asString().contains("Europe"));


    }

    /**
     * Given accept type is json
     * And Path param value is 1
     * When user send get request to /ords/hr/regions/{region_id}
     * Status code should be 200
     * Content type should be "application/json"
     * And body should contain "Europe"
     */

    @DisplayName("Get Request to ords/hr/regions/1")
    @Test
    public void getSingleRegionsWithParam(){

        int region = 1;
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",region).get("/regions/{id}");
        System.out.println(response.statusCode());

        Assertions.assertEquals(200,response.statusCode());

        response.prettyPrint();


        assertTrue(response.asString().contains("Europe"));

    }

    /**
     * Given accept type is json
     * And query param q="{"region_name": "Americas"}"
     * When user send get request to /ords/hr/regions
     * Status code should be 200
     * Content type should be "application/json"
     * And region name should be "Americas"
     * And region id should be "2
     */

    @DisplayName("Get Request to /ords/hr/regions with query param")
    @Test
    public void getRegionWithParamQuery(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q","{\"region_name\": \"Americas\"}")
               .when() .get("/regions");
        System.out.println(response.statusCode());

        assertEquals(200,response.statusCode());

        System.out.println("Content type = " + response.contentType());

        assertEquals("application/json",response.contentType());

        response.prettyPrint();
        assertTrue(response.asString().contains("Americas"));
        assertTrue(response.asString().contains("2"));

    }
}
