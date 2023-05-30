package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {


    static ValidatableResponse response;


    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/users")
                .then().statusCode(200);
    }


    // 1. Extract the all Ids
    @Test
    public void extractAllIds() {
        List<Integer> allIds = response.extract().path("id");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("All Ids are : " + allIds);
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //2. Extract the all Names
    @Test
    public void extractAllTheNames() {
        List<String> allNames = response.extract().path("name");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("All names are : " + allNames);
        System.out.println("-------------------- End Of The Test ----------------------");

    }

    //3. Extract the name of 5th object
    @Test
    public void extractTheNameOf5thObject() {

        String nameOfObject = response.extract().path("[4].name");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The name of 5th object : " + nameOfObject);
        System.out.println("-------------------- End Of The Test ----------------------");

    }

    //4. Extract the names of all object whose status = inactive
    @Test
    public void extractTheNamesOfAllObjectWhoseStatusIsInactive() {

        List<HashMap<String, ?>> nameOfAllObject = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The names of all object whose status is inactive: " + nameOfAllObject);
        System.out.println("-------------------- End Of The Test ----------------------");

    }

    //5. Extract the gender of all the object whose status = active
    @Test
    public void extractTheGenderOfAllTheObjectWhoseStatusIsActive() {

        List<HashMap<String, ?>> genderOfAllObject = response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The gender of all the object whose status is active : " + genderOfAllObject);
        System.out.println("-------------------- End Of The Test ----------------------");

    }

    //6. Print the names of the object whose gender = female
    @Test
    public void printTheNamesOfTheObjectWhoseGenderIsFemale() {
        List<HashMap<String, ?>> namesOfObject = response.extract().path("findAll{it.gender=='female'}.name");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The names of the object whose gender is female : " + namesOfObject);
        System.out.println("-------------------- End Of The Test ----------------------");

    }

    //7. Get all the emails of the object where status = inactive
    @Test
    public void getAllTheEmailsOfTheObjectWhereStatusIsInactive() {
        List<HashMap<String, ?>> allTheEmailsList = response.extract().path("findAll{it.status=='inactive'}.email");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("All the emails of the object where status is inactive : " + allTheEmailsList);
        System.out.println("-------------------- End Of The Test ----------------------");

    }

    //8. Get the ids of the object where gender = male
    @Test
    public void getTheIdsOfTheObjectWhereGenderIsMale() {
        List<HashMap<String, ?>> idsOfTheObject = response.extract().path("findAll{it.gender== 'male'}.id");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("The ids of the object where gender is male : " + idsOfTheObject);
        System.out.println("-------------------- End Of The Test ----------------------");

    }

    //9. Get all the status
    @Test
    public void getAllTheStatus() {
        List<String> statusList = response.extract().path("status");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("All the status are : " + statusList);
        System.out.println("-------------------- End Of The Test ----------------------");

    }

    //10. Get email of the object where name = Subhash Abbott
    @Test
    public void getEmailOfTheObjectWhereNameIsSubhashAbbott() {
        List<String> emailOfTheObject = response.extract().path("findAll{it.name == 'Subhash Abbott'}.email");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("Email of the object where name is Karthik Dubashi IV : " + emailOfTheObject);
        System.out.println("-------------------- End Of The Test ----------------------");

    }

    //11. Get gender of id = 2272442
    @Test
    public void getGenderOfId5471() {
        List<String> genderId = response.extract().path("findAll{it.id == 2272442}.gender");
        System.out.println("--------------------Start Of The Test ----------------------");
        System.out.println("Gender's Id 2272442 is: " + genderId);
        System.out.println("-------------------- End Of The Test ----------------------");

    }
}
