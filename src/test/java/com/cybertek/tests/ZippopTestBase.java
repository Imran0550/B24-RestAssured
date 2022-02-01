package com.cybertek.tests;

import com.cybertek.utilities.ConfigurationReader;
import com.cybertek.utilities.ConfigurationReader;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class ZippopTestBase {

    @BeforeAll
    public static void setUp(){
        System.out.println("This comes from test base class");
        baseURI = ConfigurationReader.getProperty("zipop.url");
    }
}
