package com.cybertek.tests.day01_intro_to_api;

import com.cybertek.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReqResGetUsersTes {




    @Test
    public void getAllUsersApiTest(){

        String url = "https://reqres.in/api/users";

        Response response = RestAssured.get(url);

        System.out.println(response.statusCode());
        Assertions.assertEquals(200,response.statusCode());


        Assertions.assertTrue(response.prettyPrint().contains("George"));
    }


    @Test
    public void getSingleUserApiTest(){

        Response response = RestAssured.get(ConfigurationReader.getProperty("url"));

        System.out.println(response.statusCode());

        //verify the code is 200
        Assertions.assertEquals(200,response.statusCode());

       response.prettyPrint();

       Assertions.assertTrue(response.asPrettyString().contains("Charles"));
       Assertions.assertTrue(response.prettyPrint().contains("5"));
    }

}
