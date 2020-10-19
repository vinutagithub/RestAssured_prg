package com.restAssuredBDD;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

public class UserApiTestSerialization {

    @Test(priority = 1)
    public void tc_PostUsingSerialization(){
        CreateUser createUser=new CreateUser();
        createUser.setName("John");
        createUser.setJob("QA");

        given()
                .contentType(ContentType.JSON)
                .body(createUser)
        .when()
                .post("https://reqres.in/api/users")
        .then()
                .statusCode(201);
    }

    @Test(priority = 2)
    public void tc_GetusingDeSerialization(){
        CreateUser createUser= RestAssured.get("https://reqres.in/api/users").as(CreateUser.class);
        createUser.getuser();
    }
}
