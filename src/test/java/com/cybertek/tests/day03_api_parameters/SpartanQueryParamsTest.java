package com.cybertek.tests.day03_api_parameters;

import com.cybertek.utilities.ConfigurationReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;

public class SpartanQueryParamsTest {

    @BeforeAll
    public static   void setUp(){
        baseURI = ConfigurationReader.getProperty("spartan.url");
    }

    /**
     * Given accept type is Json
     *         And query parameter values are:
     *         gender|Female
     *         nameContains|e
     *         When user sends GET request to /api/spartans/search
     *         Then response status code should be 200
     *         And response content-type: application/json
     *         And "Female" should be in response body
     *         And "Janette" should be in response body
     */

    @Test
    public void searchForSpartanQueryTest(){

    }



}
