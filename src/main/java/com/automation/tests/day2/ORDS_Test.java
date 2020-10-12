package com.automation.tests.day2;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;


public class ORDS_Test {
    String BASE_URI ="http://3.92.174.162:1000/ords/hr";

    @Test
    @DisplayName("Get List of all employees")
    public void getAllEmployees(){
       Response response =  given().baseUri(BASE_URI).
                        when().
                               get("/employees").prettyPeek();

       response.then().statusCode(200);



    }
    @Test
    @DisplayName("Get Specific Employee")
    public void getOneEmployee(){

        Response response =  given().baseUri(BASE_URI).
                when().
                get("/employees/{id}",100).prettyPeek();

        int statusCode = response.statusCode();
        Assertions.assertEquals(200, statusCode);


    }

    @Test
    public void getCountryName(){
        Response response =  given().baseUri(BASE_URI).
                when().
                get("/countries").prettyPeek();

        int statusCode = response.statusCode();
        Assertions.assertEquals(200, statusCode);


    }






}
