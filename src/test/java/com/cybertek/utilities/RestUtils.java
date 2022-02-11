package com.cybertek.utilities;

import com.cybertek.tests.pojo.Spartan;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
public class RestUtils {

    /**
     * accepts spartan ID and returns pojo object containing spartan info
     * Spartan sp10 = RestUtils.getSpartan(10)
     * sp10 will have all info of spartan id 10
     * @param spartanId
     * @return
     */

    public static Spartan getSpartan (int spartanId){

     Spartan spartan = given().accept(ContentType.JSON)
                .pathParam("id",spartanId)
                .when().get(ConfigurationReader.getProperty("spartan.url") + "api/spartans/{id}")
        .then().assertThat().statusCode(200)
        .and().extract().body().as(Spartan.class);


     return spartan;

    }

}
