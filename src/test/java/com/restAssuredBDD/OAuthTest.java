package com.restAssuredBDD;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.config.LogConfig.logConfig;
import static io.restassured.config.RestAssuredConfig.config;
import static io.restassured.config.RestAssuredConfig.newConfig;


public class OAuthTest {

    //base url- http://coop.apps.symfonycasts.com
    //base path - /api/USER_ID/eggs-count



    @Test
    public void test_OAuth(){
        String id="1419";



        String token=
                given()
                        .formParam("client_id","API_BDD")
                        .formParam("client_secret","7f2fdf984b4c12c2ae9453343285056d")
                        .formParam("grant_type","client_credentials")
                .when()
                        .post("http://coop.apps.symfonycasts.com/token")
                .then()
                        .log().all()
                        .extract().path("access_token");

        given()
                .pathParam("userid",id)
                .formParam("access_token",token)

        .when()
                .post("http://coop.apps.symfonycasts.com/api/{userid}/eggs-count") //using path parameter
        .then()
                .assertThat().body("success",equalTo(true))
                .log().all();
    }
}
