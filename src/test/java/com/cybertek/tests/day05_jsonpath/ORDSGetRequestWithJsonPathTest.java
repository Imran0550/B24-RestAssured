package com.cybertek.tests.day05_jsonpath;

import com.cybertek.tests.ORDSTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
public class ORDSGetRequestWithJsonPathTest extends ORDSTestBase {

    /**
     *Given accept is json
     * when I send get request to ords/hr/employees/103
     * Then status code is 200
     * and content type header is json
     * and employee_id is 103
     * and first_name is Alexander
     * ane last_name 9s Hunold
     * and salary is 9000
     */

    @DisplayName("GET ords/hr/employees/103 and jsonPath")
    @Test
    public void jsonPathSingleEmpInfoTest() {

        Response response = given().accept(ContentType.JSON)
                            .and().when().get("/employees/103");
        System.out.println(response.statusCode());
        Assertions.assertEquals(200,response.statusCode());

        System.out.println("Content type = " +response.contentType());
        Assertions.assertEquals("application/json",response.contentType());

        //assign json response body to jsonPath
        JsonPath json = response.jsonPath();
        //read values from jsonPath object
        int empId = json.getInt("employee_id");
        String firstName = json.getString("first_name");
        String lastName = json.getString("last_name");
        int salary = json.getInt("salary");


        json.prettyPrint();

        System.out.println("emp id = " +empId);
        System.out.println("firstName = " + firstName);
        System.out.println("lastName = " + lastName);
        System.out.println("salary = " + salary);
        //verification of above info
        Assertions.assertEquals(103,empId);
        Assertions.assertEquals("Alexander",firstName);
        Assertions.assertEquals("Hunold",lastName);
        Assertions.assertEquals(9000,salary);
    }

    @DisplayName("Get ords/hr/employees and using jsonPath filters")
    @Test
    public void jsonPathFilterTest(){

        Response response = given().accept(ContentType.JSON)
                            .when().get("/employees");

        System.out.println("Status code = " + response.statusCode());

        response.prettyPrint();

        JsonPath json = response.jsonPath();
        //names of employees that work in department 90
       List<String> empList = json.getList("items.findAll {it.department_id =90}.first_name");
        System.out.println(empList.size());
        System.out.println("empList = " + empList);

        //names of employees who are IT programmer
        List<String> itList = json.getList("items.findAll {it.job_id == 'IT_PROG'}.first_name");
        System.out.println(itList);

        //emp ids of Employees whose salary is more than 5000
        List<Integer> empIds = json.getList("items.findAll {it.salary >= 5000}.employee_id");
        System.out.println(empIds);
        System.out.println("number of employees = " + empIds.size() );

        //find the person first_name with maximum salary
        String firstPersonMaxSalary = json.getString("items.max {it.salary}.first_name");
        System.out.println("Name of the person with highest salary = "+firstPersonMaxSalary);
    }
}
