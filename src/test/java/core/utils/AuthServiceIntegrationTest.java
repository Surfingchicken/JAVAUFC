package core.utils;

import back.java.core.services.AuthService;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthServiceIntegrationTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:3000";
    }

    @Test
    public void testLoginSuccess() {
            String username = "administrateur";
        String password = "administrateur";

        Response response = given()
                .contentType("application/json")
                .body(new AuthService.LoginRequest(username, password))
                .when()
                .post("/auth/login")
                .then()
                .extract()
                .response();

        System.out.println("Response: " + response.asString());
        assertEquals(200, response.statusCode());

        String token = response.jsonPath().getString("token");
        assertNotNull(token);
    }

    @Test
    public void testLoginFailure() {
        String username = "testuser";
        String password = "wrongpassword";

        Response response = given()
                .contentType("application/json")
                .body(new AuthService.LoginRequest(username, password))
                .when()
                .post("/auth/login")
                .then()
                .extract()
                .response();

        System.out.println("Response: " + response.asString());
        assertEquals(400, response.statusCode());

        String errorMessage = response.jsonPath().getString("error");
        assertEquals(" Le premier username or password not valid", errorMessage);
    }

    @Test
    public void testSignupSuccess() {
        String username = "newuser";
        String email = "newuser@example.com";
        String password = "newpassword";

        // Signup
        Response signupResponse = given()
                .contentType("application/json")
                .body(new AuthService.SignupRequest(username, email, password))
                .when()
                .post("/auth/signup")
                .then()
                .extract()
                .response();

        System.out.println("Signup Response: " + signupResponse.asString());
        assertEquals(201, signupResponse.statusCode());

        // Login after signup
        Response loginResponse = given()
                .contentType("application/json")
                .body(new AuthService.LoginRequest(username, password))
                .when()
                .post("/auth/login")
                .then()
                .extract()
                .response();

        System.out.println("Login Response: " + loginResponse.asString());
        assertEquals(200, loginResponse.statusCode());

        String token = loginResponse.jsonPath().getString("token");
        assertNotNull(token);
    }

    @Test
    public void testLogout() {
        String username = "administrateur";
        String password = "administrateur";

        // Login to get the token
        Response loginResponse = given()
                .contentType("application/json")
                .body(new AuthService.LoginRequest(username, password))
                .when()
                .post("/auth/login")
                .then()
                .extract()
                .response();

        System.out.println("Login Response: " + loginResponse.asString());
        assertEquals(200, loginResponse.statusCode());

        String token = loginResponse.jsonPath().getString("token");
        assertNotNull(token);

        // Logout using the token
        Response logoutResponse = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/logout")
                .then()
                .extract()
                .response();

        System.out.println("Logout Response: " + logoutResponse.asString());
        assertEquals(201, logoutResponse.statusCode());
    }

    @Test
    public void testDeleteUser() {
        String adminUsername = "administrateur";
        String adminPassword = "administrateur";

        // Login as admin to get the token
        Response loginResponse = given()
                .contentType("application/json")
                .body(new AuthService.LoginRequest(adminUsername, adminPassword))
                .when()
                .post("/auth/login")
                .then()
                .extract()
                .response();

        System.out.println("Login Response: " + loginResponse.asString());
        assertEquals(200, loginResponse.statusCode());

        String token = loginResponse.jsonPath().getString("token");
        assertNotNull(token);

        // Assume there is a user with ID 2 to delete
        int userIdToDelete = 9;

        // Delete user
        Response deleteResponse = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/users/" + userIdToDelete)
                .then()
                .extract()
                .response();

        System.out.println("Delete User Response: " + deleteResponse.asString());
        assertEquals(200, deleteResponse.statusCode());
    }

    @Test
    public void testListUsers() {
        String adminUsername = "administrateur";
        String adminPassword = "administrateur";

        // Login as admin to get the token
        Response loginResponse = given()
                .contentType("application/json")
                .body(new AuthService.LoginRequest(adminUsername, adminPassword))
                .when()
                .post("/auth/login")
                .then()
                .extract()
                .response();

        System.out.println("Login Response: " + loginResponse.asString());
        assertEquals(200, loginResponse.statusCode());

        String token = loginResponse.jsonPath().getString("token");
        assertNotNull(token);

        // List users with pagination parameters
        int page = 1;
        int result = 9;

        Response listResponse = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/users?page=" + page + "&result=" + result)
                .then()
                .extract()
                .response();

        System.out.println("List Users Response: " + listResponse.asString());
        assertEquals(200, listResponse.statusCode());

        // Validate the list of users
        List<Map<String, Object>> users = listResponse.jsonPath().getList("user");
        assertNotNull(users);
        assertEquals(result, users.size());
    }

}
