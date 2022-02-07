package com.cybertek.tests.day09_post_put;
import com.cybertek.tests.SpartanTestBase;
import com.cybertek.tests.pojo.Spartan;
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

public class SpartTanPostRequestTest extends SpartanTestBase {

    @Test
    public void postSpartanTest(){
       String jsonRequest = "       {\n" +
               "          \"gender\": \"Male\",\n" +
               "          \"name\": \"RestAssuredPost\",\n" +
               "          \"phone\": 2115552345\n" +
               "        }";

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(jsonRequest)
                .when().post("api/spartans");
        System.out.println(response.statusCode());
        System.out.println(response.body().prettyPrint());

        assertThat(response.statusCode(),is(201));

        assertThat(response.path("success"),is(equalTo("A Spartan is Born!")));
    }

    @Test
    public void postSpartanWithMapTest() {

        Map<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("name","TestMapPost");
        requestMap.put("gender","Female");
        requestMap.put("phone",67439887878L);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .when().body(requestMap).when().post("api/spartans");

        System.out.println("status code = " + response.statusCode());
        response.prettyPrint();

        /**
         * Response verifications
         * status code is 201
         * content type is json
         * name is TestMapPost
         * gender is Female
         * phone is 3211231234L
         */
        assertThat(response.statusCode(),is(201));
        assertEquals(ContentType.JSON.toString(),response.contentType());
        assertThat(response.path("data.name"),is(equalTo("TestMapPost")));
        assertThat(response.path("data.gender"),is(equalTo("Female")));
        assertThat(response.path("data.phone"),is(67439887878L));
        assertThat(response.path("data.id"),is(not(empty())));
    }

    @Test
    public void postSpartanAndVerifyWithMapTest(){
        Map<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("name","WoodenSpoon");
        requestMap.put("gender","Male");
        requestMap.put("phone",34343979698L);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(requestMap).when().post("api/spartans");

        System.out.println("status code = " + response.statusCode());

        JsonPath json = response.jsonPath();
        assertThat(json.getString("data.name"),is("WoodenSpoon"));
        assertThat(json.getString("data.gender"),is("Male"));
        assertThat(json.getLong("data.phone"),is(34343979698L));

        Map<String,Object> responseMap = json.getMap("data");
        System.out.println(responseMap);

        assertThat(responseMap.get("name"),is(equalTo("WoodenSpoon")));
        assertThat(responseMap.get("gender"),is(equalTo("Male")));
        assertThat(responseMap.get("phone"),is(34343979698L));

    }

    @Test
    public void postSpartanWithPojoTest(){
        //create object of spartan and set value

        Spartan reqSpartan = new Spartan();
        reqSpartan.setName("POJOpost");
        reqSpartan.setGender("Female");
        reqSpartan.setPhone(9877891234L);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(reqSpartan) //automatically convert spartan object to json
                .when().post("/api/spartans");
        System.out.println(response.statusCode());

        //the greatest thing I have ever been through is this situation im in right now. I'm struggling but im sure Allah is with me and I will get through this soon Insha Allah😊


        Spartan resSpartan = response.jsonPath().getObject("data",Spartan.class);
        assertThat(resSpartan.getName(),equalTo(reqSpartan.getName()));
        assertThat(resSpartan.getGender(),is(equalTo(reqSpartan.getGender())));
        assertThat(resSpartan.getPhone(),equalTo(reqSpartan.getPhone()));

        //the same approach will work for PUT request as well, that we will quickly go over in next class insha Allah
    }
}
