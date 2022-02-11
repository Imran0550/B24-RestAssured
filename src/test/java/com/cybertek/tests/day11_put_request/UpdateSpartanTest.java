package com.cybertek.tests.day11_put_request;

import com.cybertek.tests.SpartanTestBase;
import com.cybertek.tests.pojo.Spartan;
import com.cybertek.utilities.RestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class UpdateSpartanTest extends SpartanTestBase {

    /**
     * Given accept type is Json
     * And content type is json
     * When i send put request to /api/spartans/1126
     * With json body:
     * {
     *   "gender": "Male",
     *   "name": "Changed",
     *   "phone": 1234567890
     * }
     * Then status code is 200
     * And content type is json
     * And json response should contain updated values
     */

    @Test
    public void spartanUpdateTest(){



        Map<String,Object> spartan = new LinkedHashMap<>();
        spartan.put("name","NewReq");
        spartan.put("gender","Male");
        spartan.put("phone",5056749090L);


      given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(spartan)
                .when().put("api/spartans/147").then().assertThat().statusCode(204);


    }

    /**
     given content type is json
     When i send patch request to /api/spartans/1126
     With json body:
     {
     "phone": 904959403978
     }
     Then status code is 204
     */
    @Test
    public void patchSpartanTest() {
        Map<String,Object> spartanMap = new LinkedHashMap<>();
        spartanMap.put("phone",904799403978L);

        given().contentType(ContentType.JSON)
                .and().pathParam("id",147)
                .and().body(spartanMap)
                .when().patch("/api/spartans/{id}")
                .then().assertThat().statusCode(204);

        Spartan spartan = RestUtils.getSpartan(147);

        assertThat(spartan.getPhone(),is(spartanMap.get("phone")));

    }
}
