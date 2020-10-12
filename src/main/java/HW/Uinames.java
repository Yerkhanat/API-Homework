package HW;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Uinames {
    String BASE_URI = "https://cybertek-ui-names.herokuapp.com";

    @Test
    @DisplayName("No Parameter Test")
    public void noParameterTest(){

        Response response = given().baseUri(BASE_URI).
                            when().
                                get("/api").prettyPeek();
        response.then().
                statusCode(200).
                contentType("application/json; charset=utf-8").
                body("name", Matchers.notNullValue()).
                body("surname", Matchers.notNullValue()).
                body("gender",Matchers.notNullValue()).
                body("region",Matchers.notNullValue());
    }

    @Test
    @DisplayName("Gender Test")
    public void genderTest(){

        Response response = given().baseUri(BASE_URI).
                queryParam("gender","female").
                when().get("/api").prettyPeek();
        response.then().statusCode(200).contentType("application/json; charset=utf-8").
                body("gender",Matchers.is("female"));
    }

    @Test
    @DisplayName("2 Params Test")
    public void twoParamsTest(){

        Response response=given().baseUri(BASE_URI).
                queryParam("region","Russia").
                queryParam("gender","female").
                when().
                        get("/api").prettyPeek();

        response.then().statusCode(200);



    }
    @Test
    @DisplayName("Invalid Gender Test")
    public void invalidGenderTest(){
        Response response = given().baseUri(BASE_URI).
                queryParam("gender","fdemale").
                when().get("/api").prettyPeek();
        response.then().statusCode(400).
                body("error",Matchers.is("Invalid gender"));
    }

    @Test
    @DisplayName("Invalid Region Test")
    public void invalidRegionTest(){

        Response response = given().baseUri(BASE_URI).
                queryParam("region","GRRRR").
                when().get("/api").prettyPeek();

        response.then().body("error",Matchers.is("Region or language not found")).
        statusCode(400);
    }
    @Test
    @DisplayName("Amount and regions test")
    public void amountAndRegionsTest(){

        Response response =given().baseUri(BASE_URI).
                queryParam("region","Greece").
                queryParam("amount","25").
                when().
                   get("/api").prettyPeek();
        response.then().
                assertThat().
                statusCode(200).
                header("Content-Type","application/json; charset=utf-8");

        List<User>userList =response.jsonPath().getList( "",User.class);

        //System.out.println(userList);

       Set<String> fullNames = new HashSet<>();

        for (User user:userList) {
            String fullName =user.getName()+" "+ user.getSurname();
            fullNames.add(fullName);
        }

        response.then().assertThat().body("size()",Matchers.is(fullNames.size()));

        System.out.println(fullNames);



    }

    @Test
    @DisplayName("Amount and regions test")
    public void threeParamsTest(){

        Response response = given().baseUri(BASE_URI).

                queryParam("region","Greece").
                queryParam("gender","female").
                queryParam("amount","5").
                when().get("/api").prettyPeek();

        response.then().assertThat().statusCode(200).contentType("application/json; charset=utf-8").
        body("region",Matchers.everyItem(Matchers.is("Greece"))).
        body("gender",Matchers.everyItem(Matchers.is("female"))).
        body("size()", Matchers.is(5));

    }

    @Test
    @DisplayName("Amount count test")
    public void amountCountTest(){

        Response response = given().baseUri(BASE_URI).
                queryParam("amount","10").
                when().
                get("/api").prettyPeek();

        response.then().assertThat().
                statusCode(200).contentType("application/json; charset=utf-8").
                body("size()",Matchers.is(10));

    }
    }








