# Example Apache Camel API with SpringBoot and Swagger UI

to start up, run:

```
	mvn spring-boot:run
```
	
them, do a POST http request to:
	http://localhost:8080/camel/person 

with the HEADER: Content-Type: application/json, 

and a BODY Payload like {"firstname": "Rob","lastname": "Rong"}

and we will get a return code of 201 and the response: Hello, World - if the transform() method from Application class is uncommented and the process() method is commented

or return code of 201 and the response: {"firstname": "Not so clever Rob","lastname": "Hello, Rong"} - if the transform() method from Application class is commented and the process() method is uncommented 

## Swagger UI integration
show the api on...

[http://localhost:8080/camel/api-doc](http://localhost:8080/camel/api-doc)

show Swagger UI on...

[http://localhost:8080/swagger/index.html](http://localhost:8080/swagger/index.html)



## Relevant articles:

- [Apache Camel with Spring Boot](http://www.baeldung.com/apache-camel-spring-boot)
- [Spring Boot, Apache Camel, and Swagger UI](https://dzone.com/articles/spring-boot-apache-camel-and-swagger-ui-1)