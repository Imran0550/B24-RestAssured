package com.cybertek.tests.day08_hamcrest_post;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HamcrestMatcherWithApiTest {

    @Test
    public void helloWorldApiTest(){

        given().accept(ContentType.JSON)
                 .when().get("https://sandbox.api.service.nhs.uk/hello-world/hello/world")
                  .then().assertThat().statusCode(200)
                    .and().assertThat().contentType("application/json")
                      .and().body("message",equalTo("Hello World!"));
    }

    @Test
    public void reqResAllUsersTest(){
        given().accept(ContentType.JSON)
                 .when().get("https://reqres.in/api/users")
                  .then().assertThat().statusCode(200)
                    .and().assertThat().contentType(ContentType.JSON.toString())
                     .and().body("total",equalTo(12),"total_pages",is(2))
                     .and().body("data[0].first_name",is(equalTo("George")),"data[0].email", is(equalTo("george.bluth@reqres.in")));

        //using items matchers

        given().accept(ContentType.JSON)
                 .when().get("https://reqres.in/api/users")
                 .then().assertThat().body("data.id",hasSize(6),"data.id",hasItems(1,2,3,4,5,6))
                  .and().body("data.first_name",hasItems("George","Janet","Eve","Emma"))
        .log().all();
    }
}
