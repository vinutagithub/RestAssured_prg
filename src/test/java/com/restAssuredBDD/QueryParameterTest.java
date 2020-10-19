package com.restAssuredBDD;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

// Get method -> https://reqres.in/api/users?page=2 using query param()
// testing for page = 1, 2, 3 .
//
public class QueryParameterTest {

    // single data test for ?page=1
    @Test
    public void queryparam(){

        given()
            .param("page",1)
        .when()
            .get("https://reqres.in/api/users")
        .then()
            .log().body();
    }

    @DataProvider(name="Page_data")
    public  Object[] pageData(){
        Integer [] pageno= new Integer[]{3,1,2};
        return pageno;
    }

    // multiple data for ?page=pageno
    @Test(dataProvider = "Page_data")
    public void queryparam_data(int pageno){

        given()
                .param("page",pageno)
        .when()
                .get("https://reqres.in/api/users")
        .then()
                .assertThat().body("page",equalTo(pageno))
                .log().all();
                //.log().body();
    }



}
