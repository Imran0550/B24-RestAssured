package com.cybertek.tests.day07_deserialization;

import com.cybertek.tests.SpartanTestBase;
import com.cybertek.tests.pojo.AllSpartans;
import com.cybertek.tests.pojo.Spartan;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;


public class AllSpartansToPojoTest extends SpartanTestBase {

    @Test
    public void getAllSpartansTest(){
        Response response = given().accept(ContentType.JSON)
                .when().get("api/spartans");
        System.out.println("status code = " + response.statusCode());

        //AllSpartans allSpartans = response.as(AllSpartans.class);

        List<Spartan> allSpartans = response.jsonPath().getList("",Spartan.class);
        System.out.println(allSpartans.get(0));
        System.out.println("Total spartans = " + allSpartans.size());


        //loop through the list and print each spartan info in separate line:
        for (Spartan each : allSpartans){
            if(each.getGender().equalsIgnoreCase("male")){
                System.out.println(each.toString());
            }
        }
    }
}
