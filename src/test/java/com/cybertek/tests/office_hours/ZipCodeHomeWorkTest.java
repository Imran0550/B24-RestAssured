package com.cybertek.tests.office_hours;

import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ZipCodeHomeWorkTest {

    @BeforeAll
    public static void setUp(){
        baseURI = ConfigurationReader.getProperty("zipop.url");
    }

    /**
     * Given Accept application/json
     * And path zipcode is 22031
     * When I send a GET request to /us endpoint
     * Then status code must be 200
     * And content type must be application/json
     * And Server header is cloudflare
     * And Report-To header exists
     * And body should contains following information
     *     post code is 22031
     *     country  is United States
     *     country abbreviation is US
     *     place name is Fairfax
     *     state is Virginia
     *     latitude is 38.8604
     */

    @Test
    public void zipCodeTest(){

        Response response = given().accept(ContentType.JSON)
                           .and().pathParam("zipcode",22031)
                           .when().get("/us/{zipcode}");
        System.out.println("Status code = "+response.statusCode());
        assertEquals(HttpStatus.SC_OK,response.statusCode());

        System.out.println("Content type = " + response.contentType());
        assertEquals("application/json",response.contentType());

        System.out.println(response.getHeader("Server"));
        assertTrue(response.getHeader("Server").equals("cloudflare"));

        assertTrue(response.getHeaders().hasHeaderWithName("Report-To"));

        //Json body verification
        JsonPath json = response.jsonPath();

        //post code is 22031
        System.out.println("post code = " + json.getString("\"post code\""));
        assertEquals("22031",json.getString("\"post code\""));

        String country = json.getString("\"country\"");
        assertEquals("United States",country);

        String countryAbbreviation = json.getString("\"country abbreviation\"");
        assertEquals("US",countryAbbreviation);

        //add to list
        Map<String, Object> places = json.getMap("places[0]");
        System.out.println(places);

        assertEquals("Fairfax",places.get("place name"));
        assertEquals("Virginia",places.get("state"));
        assertEquals("38.8604",places.get("latitude"));

    }

    @Test
    public void zipcodeJsonToMapTest(){

        Response response = given().accept(ContentType.JSON)
                           .and().pathParam("postal-code",22031)
                            .when().get("/us/{postal-code}");

        System.out.println(response.statusCode());

        //Deserialization Json - > Map

        Map<String,Object> jsonMap = response.body().as(Map.class);
        System.out.println("jsonMap = " + jsonMap);

        System.out.println("post code = " + jsonMap.get("post code"));
        assertEquals("22031",jsonMap.get("post code"));

        System.out.println("Country = " + jsonMap.get("country"));
        assertEquals("United States",jsonMap.get("country"));

        System.out.println("country abbreviation = " + jsonMap.get("country abbreviation"));
        assertEquals("US",jsonMap.get("country abbreviation"));

        System.out.println("Place name = " + jsonMap.get("places"));

        List<Map<String,Object>> placeInfo = (List<Map<String, Object>>) jsonMap.get("places");
        System.out.println(placeInfo.get(0)); // print map object

        //print place name
        System.out.println("place name = " + placeInfo.get(0).get("place name"));


        Map<String ,Object> placeMap = placeInfo.get(0);
        System.out.println(placeMap.get("place name"));
        assertEquals("Fairfax",placeMap.get("place name"));


    }
}
