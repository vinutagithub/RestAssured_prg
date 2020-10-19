package com.restAssuredBDD;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;


public class PostRequest_demo {

    public static HashMap map=new HashMap();

    @BeforeClass
    public void payload(){
       //{"name":"test","salary":"123","age":"23"}
        map.put("name", RestUtil.empName());
        map.put("salary", RestUtil.empSalary());
        map.put("age", RestUtil.empAge());
        System.out.println(map);

        RestAssured.baseURI="http://dummy.restapiexample.com";
        RestAssured.basePath="/api/v1/create";
    }

    @Test
    public void tc_PostRequest(){


        given()
                .contentType(ContentType.JSON)
                .body(map)

        .when()
                .post()

        .then()
                .statusCode(200)
                .and()
                .body("status",equalTo("success"))
                .log().body();
    }


    // Serialization

    @Test
    public void tc_PostUsingSerialization(){

    }

}
