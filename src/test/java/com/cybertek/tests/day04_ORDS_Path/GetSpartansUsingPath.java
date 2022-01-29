package com.cybertek.tests.day04_ORDS_Path;

import com.cybertek.tests.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class GetSpartansUsingPath extends SpartanTestBase {

    /**
     * Given accept is json
     * And path param id is 13
     * When I send get request to /api/spartans/{id}
     * Then status code is 200
     * And content type is json
     * And id value is 13
     * And name is "Jaimie"
     * And gender is "Female"
     * And phone is "7842554879"
     */

    @Test
    public void readJsonWithPathTest(){

        int id = 13;
       Response response = given().accept(ContentType.JSON)
               .and().pathParam("id",id)
               .when().get("api/spartans/{id}");

       //response validation
        System.out.println("response code = "+response.statusCode());
        Assertions.assertEquals(200,response.statusCode());

        System.out.println("content type = " + response.contentType());
        assertEquals("application/json",response.contentType());


        System.out.println("id = " + response.path("id"));
        System.out.println("name = " + response.path("name"));
        System.out.println("gender = "+ response.path("gender"));
        System.out.println("phone = " + response.path("phone"));

        int id1 = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");

        //validate the above
        assertEquals(13,id1);
        assertEquals("Jaimie",name);
        assertEquals("Female",gender);
        assertEquals("7842554879",response.path("phone").toString());




    }

}
