package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class PostsAssertionTest extends TestBase {

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


    // 1. Verify the if the total record is 25
    @Test
    public void verifyTheTotalRecordIs() {
        response.body("size", equalTo(25));
    }

    //2. Verify the if the title of id = 39946 is equal to ”Conduco atque eligendi voluptas aegre."
    @Test
    public void verifyTheTitleOdId() {
        response.body("findAll{it.id==39946}.title", hasItem("Conduco atque eligendi voluptas aegre."));
    }

    //3. Check the single user_id in the Array list (2322254)
    @Test
    public void checkTheSingleUserIdInTheArrayList() {
        response.body("user_id", hasItem(2322254));
    }

    //4. Check the multiple ids in the ArrayList (2322256,2322254,2322252)
    @Test
    public void checkTheMultipleIdsinTheArrayList() {
        response.body("user_id", hasItems(2322256, 2322254, 2322252));
    }

    /*5. Verify the body of userid =  2322258 is equal “Quia credo et. Deleo absum coniecto. Virtus tamdiu absens. Ea casso cupressus. Aliqua valens urbanus. Vestrum carbo adopto. Corrupti autus adaugeo. Terreo conor appono. Sursum claustrum asperiores. Cognomen sed maiores. Ut maxime veritatis. Caelum amplexus eius.”
     */
    @Test
    public void verifyTheBodyOfUserId2322258() {
        response.body("findAll{it.user_id== 2322258}.body", hasItem("Quia credo et. Deleo absum coniecto. Virtus tamdiu absens. Ea casso cupressus. Aliqua valens urbanus. Vestrum carbo adopto. Corrupti autus adaugeo. Terreo conor appono. Sursum claustrum asperiores. Cognomen sed maiores. Ut maxime veritatis. Caelum amplexus eius."));

    }


}
