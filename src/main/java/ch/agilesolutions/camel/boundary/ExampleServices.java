package ch.agilesolutions.camel.boundary;

import ch.agilesolutions.camel.model.Person;

/**
 * a Mock class to show how some other layer (a persistence layer, for instance)
 * could be used insida a Camel
 * 
 */
public class ExampleServices {

	public static void example(Person bodyIn) {
		bodyIn.setLastname("Hello, " + bodyIn.getLastname());
		bodyIn.setFirstname("the first one");;
	}
}