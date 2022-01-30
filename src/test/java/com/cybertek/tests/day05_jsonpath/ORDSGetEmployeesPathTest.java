package com.cybertek.tests.day05_jsonpath;

import com.cybertek.tests.ORDSTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
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

    }
}
