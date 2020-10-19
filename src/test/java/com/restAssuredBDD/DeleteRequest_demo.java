package com.restAssuredBDD;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.given;

public class DeleteRequest_demo {

    @Test
    public void tc_Delete(){

        RestAssured.baseURI="https://reqres.in/api";
        RestAssured.basePath="/users/2";

        Response response=
                given()
                .when()
                        .delete()
                .then()
                        .statusCode(204)
                        .extract().response();

        Headers allheader=response.headers();
        for(Header h:allheader){
            if(h.getName().compareTo("Set-Cookie")==0){
                Assert.assertEquals(h.getValue().contains("Secure"),true);
                System.out.println( h.getName() +" - " +h.getValue());
                break;
            }
        }
    }
}
