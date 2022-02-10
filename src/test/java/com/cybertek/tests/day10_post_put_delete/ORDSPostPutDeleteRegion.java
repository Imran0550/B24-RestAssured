package com.cybertek.tests.day10_post_put_delete;

import com.cybertek.tests.ORDSTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSPostPutDeleteRegion extends ORDSTestBase {

    @Test
    public void postARegionTest(){

        //delete region by id, before posting
        deleteRegion(104);

        Map<String,Object> newRegion = new LinkedHashMap<>();
        newRegion.put("region_id",104);
        newRegion.put("region_name","Wood Region");

      Map<String,Object> region =  given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(newRegion).when().post("/regions/")
        .then().assertThat().statusCode(201)
        .and().contentType(ContentType.JSON.toString())
        .and().extract().body().as(Map.class);

        System.out.println(region);
        assertEquals(newRegion.get("region_id"),region.get("region_id"));
        assertEquals(newRegion.get("region_name"),region.get("region_name"));



        // send a get request with region_id and verify it matches the post request map
      Map<String ,Object> getRegionReq =  given().accept(ContentType.JSON)
                .when().get("/regions/104").then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().extract().body().as(Map.class);

        System.out.println("getRegionReq = " + getRegionReq);

        //verify  getRequest map is matching newRegion map
        assertEquals(newRegion.get("region_id"),getRegionReq.get("region_id"));
        assertEquals(newRegion.get("region_name"),getRegionReq.get("region_name"));
    }

    public static void deleteRegion(int regionId){
        delete("/regions/" + regionId).then().log().all();
    }
}
