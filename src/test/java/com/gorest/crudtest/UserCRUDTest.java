package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {

    static String name = "Prime" + TestUtils.getRandomValue();
    static String email = "prime" + TestUtils.getRandomValue() + "@gmail.com";
    static String gender = "Female";
    static String status = "active";


    @Test
    public void verifyUserCreatedSuccessfully() {

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 5f8f6573a47c8960d0a91e044e48aeed74916d3e1b6a5a5a8cd77338db388b67")
                .contentType(ContentType.JSON)
                .body(userPojo)
                .when()
                .post("/users");
        response.then().log().all().statusCode(201);

    }

    @Test
    public void getTheCreatedUser() {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Access", "application/json")
                .header("Authorization", "Bearer 5f8f6573a47c8960d0a91e044e48aeed74916d3e1b6a5a5a8cd77338db388b67")
                .when()
                .get("/users/2327105");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void verifyUserUpdatedSuccessfully() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName("Kai");
        userPojo.setEmail(email);
        userPojo.setGender("Male");
        userPojo.setStatus(status);

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 5f8f6573a47c8960d0a91e044e48aeed74916d3e1b6a5a5a8cd77338db388b67")
                .contentType(ContentType.JSON)
                .body(userPojo)
                .when()
                .put("/users/2327105");
        response.then().log().all().statusCode(200);
    }

    @Test
    public void verifyUserDeletedSuccessfully() {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Access", "application/json")
                .header("Authorization", "Bearer 5f8f6573a47c8960d0a91e044e48aeed74916d3e1b6a5a5a8cd77338db388b67")
                .when()
                .delete("/users/2327105");
        response.then().log().all().statusCode(204);

    }
}
