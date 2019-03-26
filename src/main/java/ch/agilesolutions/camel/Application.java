package ch.agilesolutions.camel;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.component.swagger.DefaultCamelSwaggerServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "ch.agilesolutions.camel")
public class Application {

	private static final String CAMEL_URL_MAPPING = "/api/*";
	private static final String CAMEL_SERVLET_NAME = "CamelServlet";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	ServletRegistrationBean servletRegistrationBean() {
		ServletRegistrationBean servlet = new ServletRegistrationBean(new CamelHttpTransportServlet(),
				CAMEL_URL_MAPPING);
		servlet.setName(CAMEL_SERVLET_NAME);
		return servlet;
	}

	@Bean
	public ServletRegistrationBean swaggerServlet() {
		ServletRegistrationBean swagger = new ServletRegistrationBean(new DefaultCamelSwaggerServlet(), "/api-doc/*");
		Map<String, String> params = new HashMap<>();
		params.put("base.path", "camel");
		params.put("api.title", "my api title");
		params.put("api.description", "my api description");
		params.put("api.termsOfServiceUrl", "termsOfServiceUrl");
		params.put("api.license", "license");
		params.put("api.licenseUrl", "licenseUrl");
		swagger.setInitParameters(params);
		return swagger;
	}

}