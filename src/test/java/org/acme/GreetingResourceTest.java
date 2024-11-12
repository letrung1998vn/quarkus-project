package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResourceTest {
    @Test
    void testLoginEndpoint() {
        given().queryParam("userName","admin").queryParam("password","admin")
          .when().get("/login")
          .then()
             .statusCode(200)
             .body(is("Welcome admin"));
    }

    @Test
    void testLoginEndpointFail() {
        given().queryParam("userName","admin").queryParam("password","ad")
                .when().get("/login")
                .then()
                .statusCode(400)
                .body(is("Incorrect username or password"));
    }

}