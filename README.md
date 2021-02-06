# Backend Solution for the Ista Todo List Assessment

This application demonstrates a possible backend solution to the assessment.

The solution is implemented as a server with three layers according to domain-driven design:
* Presentation-layer (main package) which contains the REST controller.
* Business-layer with a domain class and a service.
* Persistence-layer with a minimalistic entity class and a data repository.

Though for this simple application a domain-driven design might be overkill, it is used for demonstrative purposes.

Additional notes:
* Simple in-memory persistence with H2.
* The entity class is slightly different from the domain class to represent a more realistic scenario.
* For demonstration, the REST controller POST and DELETE handlers use Spring's `ResponseEntity` to achieve conformity to REST-ful standards like _Location_ header and specialized HTTP status codes.
* For simplicity, the domain class (`Reminder`) is also used as the DTO in the presentation-layer.
* A minimalistic integration test for the application was also implemented (see `TodoApplicationTests`).
* Javadoc and error handling where skipped to keep things simple.

## Requirements
For building and running the application you need:

* [JDK 11](https://openjdk.java.net/projects/jdk/11/)

## Run locally

`./mvnw spring-boot:run`

Service listens on http://localhost:8080

## Build JAR

`./mvnw clean package`

Resulting JAR package can be found in `./target`.
