package com.restAssuredBDD;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtil {

    public static String empName(){
        String generatedname= RandomStringUtils.randomAlphabetic(1);
        return("Henry_"+generatedname);
    }

    public static String empSalary(){
        String generatedsal= RandomStringUtils.randomNumeric(5);
        return(generatedsal);
    }

    public static String empAge(){
        String generatedAge= RandomStringUtils.randomNumeric(2);
        return(generatedAge);
    }
}
