package com.cybertek.tests.day06_deserialization;

import com.cybertek.tests.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanJsonToCollectionsTest extends SpartanTestBase {

    /**
     * given accept type is json
     * When I send request to /api/spartans/24
     * Then status code is 200
     * And content type is json
     * And id is 24, Name is julio, gender is male, phone number is 9393139934
     */

    @Test
    public void singleSpartanToMapTest(){

        Response response = given().accept(ContentType.JSON)
                            .when().get("api/spartans/24");
        System.out.println("Status code = " + response.statusCode());
        assertEquals(200,response.statusCode());

        System.out.println("Content type = " + response.contentType());
        assertEquals("application/json",response.contentType());

        Map<String,Object> spartanMap = response.as(Map.class);
        System.out.println(spartanMap);

        Object id = spartanMap.get("id");
        Object name = spartanMap.get("name");
        Object gender = spartanMap.get("gender");
        Object phone = spartanMap.get("phone");

        System.out.println("id = " + id);
        System.out.println("Name = " + name);
        System.out.println("Gender = " + gender );
        System.out.println("Phone number = " + phone);

        assertEquals(24,id);
        assertEquals("Julio",name);
        assertEquals("Male",gender);
        assertEquals(9393139934L,phone);


        //create a new map and compare
        Map<String ,Object> expected = new HashMap<>();
        expected.put("id",24);
        expected.put("name","Julio");
        expected.put("gender","Male");
        expected.put("phone",9393139934L);

        assertEquals(spartanMap,expected);


    }

    @Test
    public void allSpartansToMapListTest() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        List<Map<String, Object>> spartansList = response.as(List.class);
        System.out.println("spartansList = " + spartansList);

        System.out.println("first spartan info = " + spartansList.get(0));

        System.out.println("second spartan info = " + spartansList.get(1));

        System.out.println("third spartan's id = " + spartansList.get(2).get("id"));

        Map<String,Object> firstSpartan = spartansList.get(0);
        System.out.println("First spartan = " + firstSpartan);


        for(Map<String, Object> eachSpartan : spartansList){
            System.out.println(eachSpartan);
        }


        // print phone, name, id, gender using lambda expression
        spartansList.forEach(eachName -> System.out.println(eachName.get("name")));
        spartansList.forEach(eachID -> System.out.println(eachID.get("id")));
        spartansList.forEach(eachGender -> System.out.println(eachGender.get("gender")));
        spartansList.forEach(eachPhoneNum -> System.out.println(eachPhoneNum.get("phone")));

    }
}
