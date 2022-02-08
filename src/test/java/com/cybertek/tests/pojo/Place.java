package com.cybertek.tests.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Place {

    @JsonProperty("place name")
    private String placeName;
    private String longitude;
    private String state;

    @JsonProperty("state abbreviation")
    private String stateAbbreviation;
    private String latitude;


}
