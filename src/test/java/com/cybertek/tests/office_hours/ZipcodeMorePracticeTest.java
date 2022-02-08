package com.cybertek.tests.office_hours;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Phaser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ZipcodeMorePracticeTest {

    @BeforeAll
    public static void setUp() {
        baseURI = "http://api.zippopotam.us";
    }

    @Test
    public void getZipcodeInfo(){

    }
}
