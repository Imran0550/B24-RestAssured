package com.cybertek.tests.day07_deserialization;

import com.cybertek.tests.ORDSTestBase;
import com.cybertek.tests.pojo.Employee;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class AllEmployessToPojoTest extends ORDSTestBase {

    @Test
    public void allEmployeesToPojo(){

        Response response = given().accept(ContentType.JSON)
              .and().pathParam("id",100)
                .when().get("/employees/{id}");

        System.out.println(response.statusCode());

       Employee employee = response.body().as(Employee.class);
        System.out.println(employee.getEmployeeId());
        System.out.println(employee.getFirstName());
        System.out.println(employee.getLastName());
        System.out.println(employee.getDepartmentId());
        System.out.println(employee.getEmail());


    }
}
