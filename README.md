# Portfolio RESTful API

This project is a resume/portfolio REST API built with Spring Boot. It provides a set of endpoints for managing and storing information about a user's work experience, education, skills, and other relevant details that make up a typical resume. The API is designed to be easy to use and integrate into a frontend application, allowing users to create, maintain and edit their resume.

You can create, read, update, and delete your resume information. The entered data is stored using a persistent storage solution, such as MySQL database.

## Requirements

To run this project, you will need the following:

- Java 17 or higher
- Gradle 7.5 or higher (optional if using the Gradle wrapper)
- Spring Boot 3 or higher
- Docker 19 or higher and Docker Compose (optional for running the project with Docker)
- MySQL 8 or higher (optional if running a MySQL instance in docker)

You can use Docker and Docker Compose to run the project and manage the dependencies, including the MySQL database. It makes it easier to set up and run the project without the need to install and configure the dependencies manually.

### Verifying Requirements

To verify that you have the required software installed, you can use the following commands:

- To check the installed version of Java:

```
java -version
```

- To check the installed version of Gradle:

```
gradle -v
```

- To check the installed version of Docker:

```
docker --version
```

These commands should return the installed versions of the required software. If any of the above commands return an error, it means the corresponding software is not installed or is not in the system's PATH. You can install the missing software using your system's package manager or download the latest version from the official website.

## Running the Project

To run the project, you have two options:

You can run the entire application (including the MySQL instance) using Docker Compose with the following command:

```
docker-compose up -d
```

Alternatively, you can run the MySQL instance using Docker Compose and start the Spring Boot application manually using the following commands:

```
docker-compose up -d db
./gradlew bootRun
```

To check if the application is running, you can access the API at http://localhost:8080.

## Directory Structure

The project is organized into the following directories and packages:

| Directory/Package                             | Description |
|-----------------------------------------------|-------------|
| src/main/java/dev/.../...portfolio/repository | Contains the repository classes for accessing and managing the resume data in the persistent storage |
| src/main/java/dev/.../...portfolio/model      | Contains the domain model classes, representing the entities and their relationships |
| src/main/java/dev/.../...portfolio/controller | Contains the controller classes, handling the incoming HTTP requests and mapping them to the appropriate repository methods |
| src/main/java/dev/.../...portfolio/service    | Contains the service classes, implementing the business logic and interacting with the repository classes |
| src/main/resources                            | Configuration files and other resources |
| src/test/java/dev/.../...portfolio        | Unit and integration tests for the project, organized by layer |
| src/test/resources                            | Test-specific resources, such as test data and configuration files |
| docker                                        | Docker configuration files for running the project with Docker |

## API Documentation

To view the documentation for the API, you can access the following URL:

http://localhost:8080/api/v1/doc

This will display the API documentation, which includes a list of available endpoints, the HTTP methods supported by each endpoint, and a description of the request and response payloads. You can use this documentation to learn more about the API and how to use it.

## Recommended IDEs for Java

There are many free IDEs that you can use for Java development. Some popular options include:

- [Eclipse](https://www.eclipse.org/): Eclipse is a popular open-source IDE that supports Java and other languages. It offers a wide range of tools and features, including code completion, refactoring, and debugging.

- [IntelliJ IDEA](https://www.jetbrains.com/idea/): IntelliJ IDEA is a powerful commercial IDE that offers a free community edition for Java development. It includes advanced features such as code refactoring, debugging, and testing tools.

Take into consideration that there are other IDE but according to [Snyk 2021 Report](https://snyk.io/jvm-ecosystem-report-2021/), most of the JVM developers use Eclipse and Intellij, but the use of Visual Studio code is growing in the last year.

## Best Practices

To ensure that your project is well-designed and maintainable, it is important to follow some best practices and avoid common pitfalls. Here are some recommendations based on articles written by [Vlad Mihalcea](https://vladmihalcea.com/) and [Thorben Janssen](https://www.thoughts-on-java.org/):

- [N+1 query problem with JPA and Hibernate](https://vladmihalcea.com/n-plus-1-query-problem/)
- [ManyToOne JPA and Hibernate association best practices](https://vladmihalcea.com/manytoone-jpa-hibernate/)
- [6 Performance Pitfalls when using Spring Data JPA](https://thorben-janssen.com/6-performance-pitfalls-when-using-spring-data-jpa/)
- [How to synchronize bidirectional entity associations with JPA and Hibernate](https://vladmihalcea.com/jpa-hibernate-synchronize-bidirectional-entity-associations/)

By following these recommendations, you can improve the performance and maintainability of your project.

