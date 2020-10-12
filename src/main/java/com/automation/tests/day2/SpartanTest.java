package com.automation.tests.day2;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

public class SpartanTest {

    String BASE_URI ="http://3.92.174.162:8000";

    @Test
    @DisplayName("Get list of all spartans")
    public void getAllSpartans(){

        Response response = given().baseUri(BASE_URI).
                when().
                    get("/api/spartans").prettyPeek();
        response.then().statusCode(200);
    }

    @Test
    @DisplayName("Adding spartans")
    public void addSpartan(){
        File jsonFile= new File(System.getProperty("user.dir")+"/spartan.json");
        String body ="{ \"gender\": \"Male\", \"name\": \"Yerkhanat\", \"phone\": 7144001666} ";
        Response response =given().body(jsonFile).
                given().contentType(ContentType.JSON).
                         baseUri(BASE_URI).
                when().
                         post("api/spartans").prettyPeek();
        response.then().statusCode(201);



    }

}
