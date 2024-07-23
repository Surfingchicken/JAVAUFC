package core.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TacheServiceIntegrationTest {

    private int createdTacheId;
    private static final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjE1LCJ1c2VybmFtZSI6ImFkbWluaXN0cmF0ZXVyIiwicm9sZXMiOjEsImlhdCI6MTcyMTc1MTkyMywiZXhwIjoxNzIxODM4MzIzfQ.ESxEhtbj8lp4V0_924xuzIHz1SGG70HLAQrwfPRki3c";

    @BeforeAll
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3000;
    }

    @Test
    @Order(1)
    public void testCreateTache() {
        String token =  "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjE1LCJ1c2VybmFtZSI6ImFkbWluaXN0cmF0ZXVyIiwicm9sZXMiOjEsImlhdCI6MTcyMTU3NDI4NiwiZXhwIjoxNzIxNjYwNjg2fQ.mc3BN8QNdKRr4IAyCmhYEJmDj5eT9gMDwvkC3Rd9O54";
        String payload = """
        {
            "nom": "Nom de la tâche",
            "description": "Description de la tâche",
            "date_debut": "2024-07-01T00:00:00Z",
            "date_fin": "2024-07-10T00:00:00Z",
            "type": "Type de la tâche",
            "createur": 15, 
            "executeur": 17
        }
        """;

        createdTacheId = given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/taches")
                .then()
                .statusCode(201)
                .body("nom", equalTo("Nom de la tâche"))
                .body("description", equalTo("Description de la tâche"))
                .body("type", equalTo("Type de la tâche"))
                .body("createurTache.id", equalTo(15))
                .body("executeurTache.id", equalTo(17))
                .extract()
                .path("id");
    }

    @Test
    @Order(2)
    public void testGetTacheById() {
        String token =  "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjE1LCJ1c2VybmFtZSI6ImFkbWluaXN0cmF0ZXVyIiwicm9sZXMiOjEsImlhdCI6MTcyMTU3NDI4NiwiZXhwIjoxNzIxNjYwNjg2fQ.mc3BN8QNdKRr4IAyCmhYEJmDj5eT9gMDwvkC3Rd9O54";

        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when()
                .get("/taches/" + createdTacheId)
                .then()
                .statusCode(200)
                .body("id", equalTo(createdTacheId))
                .body("nom", equalTo("Nom de la tâche"))
                .body("description", equalTo("Description de la tâche"))
                .body("type", equalTo("Type de la tâche"))
                .body("createurTache.id", equalTo(15))
                .body("executeurTache.id", equalTo(17));
    }

    @Test
    @Order(3)
    public void testUpdateTache() {
        String token =  "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjE1LCJ1c2VybmFtZSI6ImFkbWluaXN0cmF0ZXVyIiwicm9sZXMiOjEsImlhdCI6MTcyMTU3NDI4NiwiZXhwIjoxNzIxNjYwNjg2fQ.mc3BN8QNdKRr4IAyCmhYEJmDj5eT9gMDwvkC3Rd9O54";

        String payload = """
        {
            "nom": "Nom de la tâche mise à jour",
            "description": "Description mise à jour",
            "date_debut": "2024-07-05T00:00:00.000Z",
            "date_fin": "2024-07-15T00:00:00.000Z",
            "type": "Type mis à jour",
            "createur": { "id": 15 },
            "executeur": { "id": 17 }
        }
        """;

        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .patch("/taches/" + 1)
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("nom", equalTo("Nom de la tâche mise à jour"))
                .body("description", equalTo("Description mise à jour"))
                .body("date_debut", equalTo("2024-07-05T00:00:00.000Z"))
                .body("date_fin", equalTo("2024-07-15T00:00:00.000Z"))
                .body("type", equalTo("Type mis à jour"))
                .body("createurTache.id", equalTo(15))
                .body("executeurTache.id", equalTo(17));
    }

    @Test
    @Order(4)
    public void testDeleteTache() {
        String token =  "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjE1LCJ1c2VybmFtZSI6ImFkbWluaXN0cmF0ZXVyIiwicm9sZXMiOjEsImlhdCI6MTcyMTU3NDI4NiwiZXhwIjoxNzIxNjYwNjg2fQ.mc3BN8QNdKRr4IAyCmhYEJmDj5eT9gMDwvkC3Rd9O54";

        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when()
                .delete("/taches/" + createdTacheId)
                .then()
                .statusCode(200); // 204 No Content
    }
    @Test
    @Order(5)
    public void testListTaches() {
        given()
                .header("Authorization", "Bearer " + TOKEN)
                .contentType(ContentType.JSON)
                .when()
                .get("/taches/list")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", hasSize(greaterThan(0)))
                .body("[0].id", notNullValue())
                .body("[0].nom", equalTo("test"))
                .body("[0].description", equalTo("test"))
                .body("[0].date_debut", equalTo("2024-08-18T00:00:00.000Z"))
                .body("[0].date_fin", equalTo("2024-08-19T00:00:00.000Z"))
                .body("[0].type", equalTo("tache"))
                .body("[0].createurTache.id", equalTo(15))
                .body("[0].createurTache.username", equalTo("administrateur"))
                .body("[0].createurTache.email", equalTo("administrateur@gmail.com"))
                .body("[0].executeurTache.id", equalTo(15))
                .body("[0].executeurTache.username", equalTo("administrateur"))
                .body("[0].executeurTache.email", equalTo("administrateur@gmail.com"));
    }
}
