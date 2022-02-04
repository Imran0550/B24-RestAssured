package com.cybertek.tests.day07_deserialization;

import com.cybertek.tests.SpartanTestBase;
import com.cybertek.tests.pojo.AllSpartans;
import com.cybertek.tests.pojo.Spartan;
import com.cybertek.tests.pojo.SpartanSearch;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @Test
    public void searchSpartansToPojoTest() {

        Map<String,String> spartanMap = new HashMap<>();
        spartanMap.put("nameContains","ai");

        Map<String,String> spartanMap1 = new HashMap<>();
        spartanMap1.put("gender","female");

        Response response = given().accept(ContentType.JSON)
                           .queryParams(spartanMap)
                           .and().queryParams(spartanMap1)
                             .when().get("api/spartans/search");

        System.out.println(response.statusCode());

        SpartanSearch spartanSearch = response.as(SpartanSearch.class);
        System.out.println("total elements = " + spartanSearch.getTotalElement());
        assertEquals(3,spartanSearch.getTotalElement());
        System.out.println("Spartans info content = " + spartanSearch.getContent());

        //Store jaimie info into separate variable

        Spartan first = spartanSearch.getContent().get(1);
        System.out.println(first);
        System.out.println("id = " + first.getId());
        System.out.println("name = " + first.getName());
        System.out.println("gender = " + first.getGender());
        System.out.println("phone = " + first.getPhone());

        assertEquals(13,first.getId());
        assertEquals("Jaimie",first.getName());
        assertEquals("Female",first.getGender());
        assertEquals(7842554879L,first.getPhone());

    }
}
