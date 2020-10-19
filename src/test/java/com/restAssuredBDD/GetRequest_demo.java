package com.restAssuredBDD;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class GetRequest_demo {

    @BeforeMethod
    public void setup(Method m) throws FileNotFoundException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime now = LocalDateTime.now();
        String date= dtf.format(now);
        PrintStream fileOutPutStream = new PrintStream(new File(System.getProperty("user.dir")+"/src/test/logs/"+ "log_"+date+".txt"));
        RestAssured.config = config().logConfig(new LogConfig().defaultStream(fileOutPutStream));
    }

    @Test
    public void tc_getMethod_001(){
        given()
                .log().all()
                .when()
                    .get("https://api.weather.gov/alerts/active/area/CA")

                .then()
                    .statusCode(200)
                    .statusLine("HTTP/1.1 200 OK")
                    .assertThat().body("title",equalTo("current watches, warnings, and advisories for California"))
                    .assertThat().body("features[2].properties.senderName",equalTo("NWS Los Angeles/Oxnard CA"))
                    //.log().body().body("features[2].properties.senderName",equalTo("NWS Hanford CA"));
                    .log().headers();
    }
}
