package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResourceTest {
    @Test
    void testLoginEndpoint() {
        given()
          .when().get("/login?userName=admin&&password=admin")
          .then()
             .statusCode(200)
             .body(is("Welcome admin"));
    }

    @Test
    void testLoginEndpointFail() {
        given()
                .when().get("/login?userName=admin&&password=ad")
                .then()
                .statusCode(400)
                .body(is("Incorrect username or password"));
    }

}