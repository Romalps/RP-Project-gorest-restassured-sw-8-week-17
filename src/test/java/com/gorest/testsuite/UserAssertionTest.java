package com.gorest.testsuite;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class UserAssertionTest extends TestBase {

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


    @Test
    // Verify the total record is 20
    public void verifyTheTotalRecordIs20() {
        response.body("size", equalTo(20));
    }

    // Verify if the name of id = 2191530 is equal to ”Bhadran Dhawan”
    @Test
    public void verifyTheNameOfIdIs2191530EqualToBhadranDhawan() {

        response.body("findAll{it.id== 2191530}.name", hasItem("Bhadran Dhawan"));

    }

    // Check the single ‘Name’ in the Array list (Sukanya Dubashi)
    @Test
    public void checkTheSingleNameInTheArrayList() {
        response.body("name", hasItem("Sukanya Dubashi"));
    }

    // Check the multiple ‘Names’ in the ArrayList ("Bala Patel", "Mrs. Mandaakin Kaul", "Balachandra Reddy")
    @Test
    public void checkTheMultipleNamesInTheArrayList() {
        response.body("name", hasItems("Bala Patel", "Mrs. Mandaakin Kaul", "Balachandra Reddy"));
    }

    // Verify the email of userid = 2178500 is equal “amish_kaniyar@langosh-ziemann.example”
    @Test
    public void verifyTheEmailOfUseridIsEqual() {
        response.body("findAll{it.id==2178500}.email", hasItem("amish_kaniyar@langosh-ziemann.example"));
    }

    // Verify the status is “Active” of username is “Rameshwar Guha Sr.”
    @Test
    public void verifyTheStatusIsActiveOfUsernameIs() {
        response.body("findAll{it.name== 'Rameshwar Guha Sr.'}.status", hasItem("active"));
    }

    //7. Verify the Gender = male of username is “Mrs. Mandaakin Kaul”
    @Test
    public void verifyTheGenderIsMaleOfusername() {
        response.body("findAll{it.name=='Mrs. Mandaakin Kaul'}.gender", hasItem("female"));
    }


}
