package ch.agilesolutions.camel;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ch.agilesolutions.camel.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PersonResourceTest {

	@Test
	public void testGetPersonEndpoint() {
	       given()
	          .when().get("/camel/person")
	          .then()
	             .statusCode(200)  
	             .body("firstname", is("Rob"),"lastname",is("Rong")
	             );
	}

}