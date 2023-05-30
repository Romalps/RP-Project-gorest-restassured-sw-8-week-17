package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {

    static ValidatableResponse response;


    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "25")
                .get("/posts")
                .then().statusCode(200);
    }


    //1. Extract the title
    @Test
    public void extractTheTitle() {
        List<String> title = response.extract().path("title");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The title : " + title);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //2. Extract the total number of record
    @Test
    public void extractTheTotalNumberOfRecord() {
        Integer size = response.extract().path("size");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println(" The total number of record: " + size);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //3. Extract the body of 15th record
    @Test
    public void extractTheBodyOf15thRecord() {
        String body = response.extract().path("[14].body");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The body of 15th record: " + body);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //4. Extract the user_id of all the records
    @Test
    public void extractTheUserIdOfAllTheRecords() {
        List<Integer> userId = response.extract().path("user_id");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The user_id of all the records : " + userId);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //5. Extract the title of all the records
    @Test
    public void extractTheTitleOfAllTheRecords() {
        List<String> title = response.extract().path("title");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println(" The title of all the records: " + title);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //6. Extract the title of all records whose user_id = 2322241
    @Test
    public void extractTheTitleOfAllRecordsWhoseUserId() {
        List<HashMap<String, ?>> titleOfAllRecords = response.extract().path("findAll{it.user_id == 2322241}.title");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The title of all records whose user_id is 2322241 : " + titleOfAllRecords);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //7. Extract the body of all records whose id = 39932
    @Test
    public void extractTheBodyOfAllRecordsWhoseId() {
        List<String> bodyOfAllRecords = response.extract().path("findAll{it.id==39932}.body");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The body of all records whose id is 39932 : " + bodyOfAllRecords);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

}
