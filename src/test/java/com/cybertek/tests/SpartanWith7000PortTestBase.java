package com.cybertek.tests;

import com.cybertek.utilities.ConfigurationReader;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;
public class SpartanWith7000PortTestBase {


    @BeforeAll
    public static void setUp(){
        System.out.println("this is coming from base class");
        baseURI = ConfigurationReader.getProperty("spartan2.url");
    }
}
