package com.cybertek.tests.day09_post_put;

import com.cybertek.tests.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanPostRequestTest1 extends SpartanTestBase {

    @Test
    public void addNewSpartanUsingArray(){

        Map<String ,Object> newSpartan = new LinkedHashMap<>();
        newSpartan.put("name","Imran");
        newSpartan.put("gender","Male");
        newSpartan.put("phone",5499029485788L);

        Response response = given().accept(ContentType.JSON)
                 .and().contentType(ContentType.JSON)
                .and().body(newSpartan).when().post("api/spartans");

        assertThat(response.statusCode(),is(201));

        response.prettyPrint();

        //
        JsonPath json = response.jsonPath();

        Map<String,Object> spartan = json.getMap("data");
        System.out.println(spartan);

        assertThat(spartan.get("name"),is(equalTo("Imran")));
        assertEquals("Male",spartan.get("gender"));
        assertThat(spartan.get("phone"),is(5499029485788L));
    }
}
