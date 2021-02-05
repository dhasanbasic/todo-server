# Backend Example Solution for the Ista Todo List Assessment

This application demonstrates a possible backend solution to the assessment.

Features:
* H2-based persistence-layer with an entity class, a repository and some sample data.
* Business-layer with a simple service.
  * For demonstrative purposes the domain class is slightly different from the entity class.
* Presentation-layer with a simple REST-ful service conforming to standards.
  * For simplicity the domain class (`Reminder`) is also used as the DTO.
* A minimalistic integration test for the controller (see `TodoApplicationTests`).
  * Spring MVC Mocking is used to invoke the presentation-layer directly.

## Requirements
For building and running the application you need:

* [JDK 11](https://openjdk.java.net/projects/jdk/11/)

## Run locally

`./mvnw spring-boot:run`

Service listens on http://localhost:8080

## Build JAR

`./mvnw clean package`

Resulting JAR package can be found in `./target`.
