package br.com.ivanilson.cloudparking.controller;

import br.com.ivanilson.cloudparking.controller.DTO.ParkingCreateDTO;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTest {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest(){
        RestAssured.port = randomPort;
    }

    @Test
    void whenFindAllThenCheckResult() {

        RestAssured.given()
                .when()
                .get("/parking")
                .then()
                .statusCode(HttpStatus.OK.value());
                //.body("license[0]", Matchers.equalTo("DMS-1111"));
    }

    @Test
    void whenCreateThenCheckIsCreated() {

        ParkingCreateDTO createDTO = new ParkingCreateDTO("PEP-0001","PE", "FIAT TORO","BRANCO");

        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDTO)
                .post("/parking")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("license", Matchers.equalTo("PEP-0001"))
                .body("state", Matchers.equalTo("PE"))
                .body("model", Matchers.equalTo("FIAT TORO"))
                .body("color", Matchers.equalTo("BRANCO"));
    }
}