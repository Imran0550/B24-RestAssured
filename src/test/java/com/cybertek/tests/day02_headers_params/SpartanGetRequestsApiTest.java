package com.cybertek.tests.day02_headers_params;

import com.cybertek.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.AssertionSupport;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

public class SpartanGetRequestsApiTest {

    String baseUrl = ConfigurationReader.getProperty("spartan.url");
    /*
    • When I send a GET request to
• spartan_base_url/api/spartans
• Then Response STATUS CODE must be 200
            • And I Should see all Spartans data in JSON format

     */
    @Test
    public void getAllSpartansTest(){
        Response response = get(baseUrl + "api/spartans");
        System.out.println(response.statusCode());

        Assertions.assertEquals(200,response.statusCode());
    }

    /**
     * Given Accept type is "application/json"
     * When I send a GET request to
     * spartan_base_url/api/spartans
     * Then Response STATUS CODE must be 200
     * And content type should be "application/json"
     */

    @Test
    public void allSpartanHeadersTest(){
       Response response = given().accept(ContentType.JSON).
                         when().get(baseUrl + "api/spartans");

        System.out.println(response.statusCode());
        Assertions.assertEquals(200,response.statusCode());

        System.out.println(response.contentType()); //read content type response header

        Assertions.assertEquals("application/json",response.contentType());

        //print more response headers
        System.out.println("data header value =  " + response.getHeader("Date"));


        Assertions.assertTrue(response.getHeaders().hasHeaderWithName("Date"));


        System.out.println("transfer encoding value = "+response.getHeader("Transfer-Encoding"));
        Assertions.assertTrue(response.getHeaders().hasHeaderWithName("Transfer-Encoding"));



    }
}
