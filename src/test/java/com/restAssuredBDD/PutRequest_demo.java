package com.restAssuredBDD;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PutRequest_demo {
    public static HashMap<String,Object> hmap= new HashMap<>();

    String empName=RestUtil.empName();
    String empAge=RestUtil.empAge();
    String empSalary=RestUtil.empSalary();
    int emp_id=23;

    @BeforeClass
    public void putdata(){

       // hmap.put("name",empName);
        hmap.put("salary",empSalary);
        hmap.put("age",empAge);


    }

    @Test
    public void tc_get(){
        given()

        .when()
                .get("http://dummy.restapiexample.com/api/v1/employee/23")
        .then()
                .log().all();
    }

    @Test
    public void tc_put(){
        RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
        RestAssured.basePath="/update/"+emp_id;
        given()
                .contentType(ContentType.JSON)
                .body(hmap)
        .when()
                .put()
        .then()
                .statusCode(200)
                .log().all();
    }
}
