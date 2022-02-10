package com.cybertek.tests.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Region {

    private int region_id;
    private String region_name;

   private List<Link> links;
}
