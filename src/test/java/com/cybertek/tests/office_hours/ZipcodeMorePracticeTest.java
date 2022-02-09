package com.cybertek.tests.office_hours;

import com.cybertek.tests.pojo.zipcode.Place;
import com.cybertek.tests.pojo.zipcode.ZipInfo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Phaser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ZipcodeMorePracticeTest {

    @BeforeAll
    public static void setUp() {
        baseURI = "http://api.zippopotam.us";
    }

    @Test
    public void zipCodePojoTest(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/us/12189");

        assertThat(response.statusCode(),is(200));

        //de-serialization of json response body
       ZipInfo zipInfo = response.as(ZipInfo.class);

        System.out.println(zipInfo);

        assertThat(zipInfo.getPostCode(),is("12189"));
        assertThat(zipInfo.getCountry(),is(equalTo("United States")));

        System.out.println(zipInfo.getPlaces().get(0).getPlaceName());

        Place place = zipInfo.getPlaces().get(0);

        assertThat(place.getPlaceName(),is("Watervliet"));
        assertThat(place.getState(),equalTo("New York"));
        assertThat(place.getLatitude(),equalTo("42.7298"));
    }

    @Test
    public void zipcodeHamcrestChainingTest(){
        given().accept(ContentType.JSON)
                .when().get("/us/20171")
        .then().assertThat().statusCode(200)
        .and().contentType(ContentType.JSON)
        .and().body("\"post code\"",is("20171"), "country",is("United States"),"'country abbreviation'",equalTo("US"),
                "places[0].'place name'",equalTo("Herndon"),"places[0].longitude",equalTo("-77.3928"),"places[0].state",equalTo("Virginia"),"places[0].'state abbreviation'",equalTo("VA"),"places[0].latitude",equalTo("38.9252"));
    }
}
