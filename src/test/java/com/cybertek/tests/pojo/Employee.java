package com.cybertek.tests.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Employee {


   @JsonProperty("employee_id")
   private int employeeId;
   @JsonProperty("first_name")
   private String firstName;
   @JsonProperty("last_name")
   private String lastName;
   @JsonProperty("email")
   private String email;
   @JsonProperty("phone_number")
   private String phoneNumber;
   @JsonProperty("hire_date")
   private String hireDate;
   @JsonProperty("job_id")
   private String jobId;
   @JsonProperty("salary")
   private int salary;
   @JsonProperty("commission_pct")
   private String commissionPct;
   @JsonProperty("manager_id")
   private int managerId;
   @JsonProperty("department_id")
   private int departmentId;

   private List<Links> links;
}
