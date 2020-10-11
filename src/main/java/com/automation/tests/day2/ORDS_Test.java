package com.automation.tests.day2;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;


public class ORDS_Test {
    String BASE_URI ="http://3.92.174.162:1000/ords/hr";

    @Test
    @DisplayName("Get List of all employees")
    public void getAllEmployees(){
                given().baseUri(BASE_URI).
                        when().
                               get("employees").prettyPeek().
                        then().
                               statusCode(200);




    }


}
