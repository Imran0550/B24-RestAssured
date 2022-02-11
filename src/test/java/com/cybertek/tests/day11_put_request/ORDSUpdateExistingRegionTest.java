package com.cybertek.tests.day11_put_request;

import com.cybertek.tests.ORDSTestBase;
import com.cybertek.tests.pojo.Region;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ORDSUpdateExistingRegionTest extends ORDSTestBase {

    /**
     * Given accept type is Json
     * And content type is json
     * When i send put request to /regions/999
     * With json body:
     * {
     * "region_id": 999,
     * "region_name": "Wooden Region"
     * }
     * Then status code is 200
     * And content type is json
     * And json response shoul contain updated values
     */

    @Test
    public void updateExistingRegionTest() {
        Map<String, Object> region = new LinkedHashMap<>();
        region.put("region_id", 104);
        region.put("region_name", "Wood Region");

        Region region1 = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(region)
                .when().put("/regions/104")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().extract().body().as(Region.class);


        //verify the information is matching
        assertThat(region1.getRegion_id(), is(equalTo(region.get("region_id"))));
        assertEquals(region.get("region_name"), region1.getRegion_name());

    }

}
