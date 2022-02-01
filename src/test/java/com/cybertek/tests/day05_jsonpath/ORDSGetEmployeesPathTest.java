package com.cybertek.tests.day05_jsonpath;

import com.cybertek.tests.ORDSTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class ORDSGetEmployeesPathTest extends ORDSTestBase {

    @Test
    public void getAllITProgrammersTest() {

      //query parameter with hashmap
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("q","{\"job_id\":\"IT_PROG\"}");

        Response response = given().accept(ContentType.JSON)
                           .and().queryParams(paramMap)
                           .when().get("/employees");

        System.out.println("Status code = "+response.statusCode());

        response.prettyPrint();

        //print first employees emp id, first name, last name, email
        System.out.println("first emp id = " + response.path("items[0].employee_id"));
        System.out.println("first emp firstname = " + response.path("items[0].first_name"));
        System.out.println("first emp lastname = " + response.path("items[0].last_name"));
        System.out.println("first emp email = " + response.path("items[0].email") + "@gmail.com");

        //you want to email all IT_PROGs, save all emails into list of String

        List<String> allEmails = response.path("items.email");
        System.out.println("all emails " + allEmails);

        //save all the phone numbers in a list
        List<String> allPhones = response.path("items.phone_number");
        System.out.println("all phones " + allPhones);
    }
}
