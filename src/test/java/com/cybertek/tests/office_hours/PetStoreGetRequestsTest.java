package com.cybertek.tests.office_hours;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PetStoreGetRequestsTest {

    @BeforeAll
    public static void setUp() {
        baseURI = "https://petstore.swagger.io/v2";
    }

    /**
     * accept type is json
     * get request to /store/inventory
     * Then status code is 200
     * And content type is json
     * and available is 573
     */

    @Test
    public void getInventoryTest() {

        Response response = given().accept(ContentType.JSON)
                          .and().get("/store/inventory");
        System.out.println("Status code = "+response.statusCode());
        assertEquals(200,response.statusCode());

        System.out.println("Content type = " + response.contentType());
        assertEquals("application/json",response.contentType());

        //get date header
        System.out.println("Date = " + response.getHeader("Date"));
        assertTrue(response.getHeaders().hasHeaderWithName("Date"));

        //convert to jsonPath
        JsonPath json = response.jsonPath();

        //print available count
        int availableCount = json.getInt("available");
        System.out.println(availableCount);

        assertTrue(availableCount >= 500);



    }

    /**
     * accept type is json
     * order id is 2
     * get request to store/order/2
     * Then status code is 200
     * And content type is json
     * id is 2
     * pet id is 20
     * status is "placed"
     * complete is true
     * */

    @Test
    public void getOrderPathParamTest() {

        Response response = given().accept(ContentType.JSON)
                           .and().pathParam("orderId",2)
                           .when().get("/store/order/{orderId}");

        System.out.println("Status code = " + response.statusCode());
        assertEquals(HttpStatus.SC_OK,response.statusCode());

        System.out.println("Content type = " + response.contentType());
        assertEquals("application/json",response.contentType());

        //convert to jsonPath
        JsonPath json = response.jsonPath();


        int id = json.getInt("id");
        System.out.println("id = " + id);
        int petId = json.getInt("petId");
        System.out.println("pet id = " + petId);
        String status = json.getString("status");
        System.out.println("order status = " + status);
        boolean complete = json.getBoolean("complete");
        System.out.println("is complete = " + complete);

        assertEquals(2,id);
        assertEquals(20,petId);
        assertEquals("placed",status);
        assertEquals(true,complete);

    }

    /**
     * accept type is json
     * query param status is "available"
     * get request to /pet/findByStatus
     * Then status code is 200
     * And content type is json
     * And see all pet names
     * And all status values should be "available"
     * */

    @Test
    public void searchPetsByStatusTest() {

        Map<String,String> paramMap = new HashMap<>();

        paramMap.put("status","available");


        Response response = given().accept(ContentType.JSON)
                           .and().queryParams(paramMap).
                        when().get("/pet/findByStatus");
        System.out.println("Status code = "+response.statusCode());
        assertEquals(200,response.statusCode());

        System.out.println("Content type = " + response.contentType());
        assertEquals("application/json",response.contentType());

        JsonPath json = response.jsonPath();

        List<String> allNames = json.getList("name");
        System.out.println(allNames);

        List<String> allAvailable = json.getList("status");
        System.out.println(allAvailable);

        //verify all status are available
        for(String each : allAvailable){
            assertEquals("available",each);
        }


    }
}
