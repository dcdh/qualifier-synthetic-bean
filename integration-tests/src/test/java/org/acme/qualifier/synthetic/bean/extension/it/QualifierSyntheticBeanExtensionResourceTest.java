package org.acme.qualifier.synthetic.bean.extension.it;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class QualifierSyntheticBeanExtensionResourceTest {

    @Test
    public void testHelloEndpointMartin() {
        given()
                .when().get("/qualifier-synthetic-bean-extension/Martin")
                .then()
                .statusCode(200)
                .body(is("Hello Martin"));
    }

    @Test
    public void testHelloEndpointGuillaume() {
        given()
                .when().get("/qualifier-synthetic-bean-extension/Guillaume")
                .then()
                .statusCode(200)
                .body(is("Hello Guillaume"));
    }

}
