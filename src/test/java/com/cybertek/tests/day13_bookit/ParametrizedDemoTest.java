package com.cybertek.tests.day13_bookit;

import com.cybertek.utilities.ExcelUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ParametrizedDemoTest {

    public static List<String> getFriendNames() {
        List<String> friendNames = Arrays.asList("Kalys", "Tach", "Dzhamilya","Naserahmad", "Fahima", "Jama","Meeer");
        return friendNames;
    }

    @ParameterizedTest
    @MethodSource("getFriendNames")
    public void friendNamesTest(String name) {
        System.out.println("name = " + name);
    }

    /**
     * read all credentials from BookItQA3.xlsx file
     * and return as list of maps
     * Map will have 2 keys, email and password
     * @return
     */
    public static List<Map<String, String>> getAllUserCredentials() {

        //open excel file/sheet
        ExcelUtil excelUtil = new ExcelUtil("BookItQa3.xlsx","QA3");
        //returns excel data as list of amps
        return  excelUtil.getDataList();


    }


    @ParameterizedTest
    @MethodSource("getAllUserCredentials")
    public void bookItUsersTest(Map<String,String> credentials){

        System.out.println("credentials = " + credentials);
    }

    @ParameterizedTest
    @CsvSource({
            "1,4",
            "10, 3",
            "5 , 9",
            "44  , 43"
    })
    public void addNumbers (int num1, int num2){


        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);

        System.out.println("sum = " + (num1 + num2));
    }
}
