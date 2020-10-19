package com.restAssuredBDD;

//Testcase for - Api http://ergast.com/api/f1/2017/circuits.json


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PathparameterTest {

    String year = "2017";

    // we are using pathparam for season .
    //verifying data from the body using equalTo, hasItem, hasSize,hasItems
    @Test
    public void test_ApiWithPathParameter(){


                 given()
                        .pathParam("season",year)
                .when()
                        .get("http://ergast.com/api/f1/{season}/circuits.json")
                .then()
                        .assertThat().body("MRData.CircuitTable.Circuits[0].Location.country",equalTo("Australia"))
                        .and()
                        .assertThat().body("MRData.CircuitTable.Circuits.circuitId",hasSize(20))
                        .and()
                        .assertThat().body("MRData.CircuitTable.Circuits.Location.country",hasItem("Azerbaijan"))
                        .and()
                        .assertThat().body("MRData.CircuitTable.Circuits.circuitName",hasItems("Silverstone Circuit","Sochi Autodrom","Shanghai International Circuit"))
                        .log().all();

    }


    /*Above testcase using dataprovider*/

    @DataProvider(name="DataRecord_pathparameter")
    public Object[][] testDataRecord_pathparameter(){
        return new Object[][]{
                {2017,20},
                {2018,21},
                {1967,11}
        };
    }

    @Test(dataProvider = "DataRecord_pathparameter")
    public void test_ApiWithpathParameter(int year,int totalRace){

        ArrayList<String> responsebody=new ArrayList<>();

        responsebody=

        given()
                .pathParam("season",year)
       .when()
                .get("http://ergast.com/api/f1/{season}/circuits.json")
       .then()
                .assertThat().body("MRData.CircuitTable.Circuits.circuitId",hasSize(totalRace))
                .extract().path("MRData.CircuitTable.Circuits.circuitName");


        Iterator<String> it = responsebody.iterator();
        System.out.println("Following are circuitName for the Season "+ year + " Total Races "+ totalRace + " .\n");
        int i=0;
        while (it.hasNext()){
            i=i+1;
            System.out.println(i +". "+it.next());
        }
    }


}
