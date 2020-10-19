package com.restAssuredBDD;

public class CreateUser {

    public String name;
    public String job;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  String getuser(){
        return("Name : "+ name + ", Job:  "+ job);
    }


}
