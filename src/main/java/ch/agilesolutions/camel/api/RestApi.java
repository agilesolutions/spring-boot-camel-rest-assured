package ch.agilesolutions.camel.api;

import javax.ws.rs.core.MediaType;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ch.agilesolutions.camel.boundary.ExampleServices;
import ch.agilesolutions.camel.model.Person;

@Component
class RestApi extends RouteBuilder {

	@Value("${server.port}")
	String serverPort;

	@Value("${baeldung.api.path}")
	String contextPath;

	@Override
	public void configure() {

		CamelContext context = new DefaultCamelContext();

		// http://localhost:8080/camel/api-doc
		restConfiguration().contextPath(contextPath).port(serverPort).apiContextPath("/api-doc")
				.apiProperty("api.title", "Person API").apiProperty("api.description", "Just a testing purpose API")
				.apiProperty("base.path", "camel").component("servlet")
				.bindingMode(RestBindingMode.json).dataFormatProperty("prettyPrint", "true");
		/**
		 * The Rest DSL supports automatic binding json/xml contents to/from POJOs using
		 * Camels Data Format. By default the binding mode is off, meaning there is no
		 * automatic binding happening for incoming and outgoing messages. You may want
		 * to use binding if you develop POJOs that maps to your REST services request
		 * and response types.
		 */

		rest("/person/").id("add-person").post("/bean").produces(MediaType.APPLICATION_JSON)
				.consumes(MediaType.APPLICATION_JSON).bindingMode(RestBindingMode.auto).type(Person.class)
				.enableCORS(true).to("direct:remoteService");

		from("direct:remoteService").routeId("process-person").tracing().log(">>> ${body.firstname}")
				.log(">>> ${body.lastname}")
//            .transform().simple("blue ${in.body.name}")                
				.process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						Person bodyIn = (Person) exchange.getIn().getBody();

						ExampleServices.example(bodyIn);

						exchange.getIn().setBody(bodyIn);
					}
				}).setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201));

		rest("/person/").id("get-person").get().outType(Person.class).to("direct:readPerson");

		from("direct:readPerson").routeId("get-person").process(exchange -> {
			Person p = new Person();
			p.setFirstname("Rob");
			p.setLastname("Rong");
			exchange.getIn().setBody(p);
		});

	}
}