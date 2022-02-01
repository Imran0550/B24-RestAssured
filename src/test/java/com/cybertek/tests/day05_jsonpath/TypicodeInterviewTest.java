package com.cybertek.tests.day05_jsonpath;

import com.cybertek.tests.ORDSTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class TypicodeInterviewTest extends ORDSTestBase {

    @BeforeAll
    public static void setUp() {
        baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void getUserTest() {

        Response response = given().accept(ContentType.JSON)
                            .when().get("/users/1");

        System.out.println("Status code = "+response.statusCode());
        assertEquals(200,response.statusCode());

        response.prettyPrint();

        //convert response body to json path
        JsonPath json = response.jsonPath();
        String name = json.getString("name");
        String city = json.getString("address.city");
        System.out.println("name = " + name);
        System.out.println("city = " + city);

        //verification
        assertEquals("Leanne Graham",name);
        assertEquals("Gwenborough",city);

        //print out company name

        String companyName = json.getString("company.name");
        System.out.println("Company name = " + companyName);

        String lng = json.getString("address.geo.lng");
        System.out.println("lng = " + lng);
    }
}
