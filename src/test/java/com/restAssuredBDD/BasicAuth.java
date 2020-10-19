package com.restAssuredBDD;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicAuth {

    //url :http://httpbin.org/basic-auth/user/passwd
    //user and password :user, passwd
/*
    given()
    .when()
    .then()*/

    @Test
    public void tc_BasicAuth(){

        given()
                .auth().preemptive()
                .basic("user","passwd")

        .when()
                .get("http://httpbin.org/basic-auth/user/passwd")
        .then()
                .statusCode(200)
                .assertThat().body("authenticated",equalTo(true))
                .log().all();


    }








}
