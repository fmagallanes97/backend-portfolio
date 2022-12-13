# Backend Portfolio REST API

📄 This project is a resume/portfolio backend REST API built with Spring Boot. It provides a set of endpoints for managing and storing information about a user's work experience, education, skills, and other relevant details that make up a typical resume. The API is designed to be easy to use and integrate into a frontend application, allowing users to create and maintain their own resumes without the need for a dedicated server or database.

With this API, users can create, read, update, and delete their resume information. The data is stored using a persistent storage solution, such as a database, to ensure that it is available even after the application is restarted. The API also provides a way to generate a PDF version of the resume, allowing users to easily share and print their resumes.

📊 Overall, this project provides a simple and effective way for users to manage their resumes and portfolios, making it easy for them to keep their information up-to-date and share it with potential employers.

## Requirements

To run this project, you will need the following:

- Java 11 or higher
- Maven 3.6 or higher
- A compatible version of Spring Boot (3.0 or higher)
- Docker and Docker Compose (optional, for running the project with Docker)
- A persistent storage solution, such as a MySQL database, for storing the resume information

You can use Docker and Docker Compose to run the project and manage the dependencies, including the MySQL database. This makes it easier to set up and run the project without the need to install and configure the dependencies manually.

### Verifying Requirements

To verify that you have the required software installed, you can use the following commands:

- To check the installed version of Java:

```
java -version
```

- To check the installed version of Maven:

```
mvn -version
```

- To check the installed version of Spring Boot:

```
spring --version
```

These commands should return the installed versions of the required software. If any of the commands return an error, it means that the corresponding software is not installed or is not in the system's PATH. You can install the missing software using your system's package manager or by downloading the latest version from the official website.

## Frequently Asked Questions

### Do I need to install Java and Maven separately?

Yes, you will need to have Java 11 or higher and Maven 3.6 or higher installed on your system. You can download and install these tools from their official websites, or you can use your system's package manager to install them.

### Can I use a different version of Spring Boot?

Yes, you can use a different version of Spring Boot as long as it is compatible with the other dependencies of the project. However, it is recommended to use the same version as specified in the requirements to avoid any potential compatibility issues.

### Do I need to have Docker and Docker Compose installed?

No, you do not need to have Docker and Docker Compose installed to run the project. These tools are optional and are only recommended if you want to use Docker to manage the dependencies and run the project.

### Can I use a different persistent storage solution?

Yes, you can use a different persistent storage solution, such as a different type of database or a cloud storage service. However, you will need to configure the project accordingly and ensure that it is compatible with the chosen storage solution.

### What are some free IDEs that I can use for Java?

There are many free IDEs that you can use for Java development. Some popular options include:

- [Eclipse](https://www.eclipse.org/): Eclipse is a popular open-source IDE that supports Java and other languages. It offers a wide range of tools and features, including code completion, refactoring, and debugging.

- [IntelliJ IDEA](https://www.jetbrains.com/idea/): IntelliJ IDEA is a powerful commercial IDE that offers a free community edition for Java development. It includes advanced features such as code refactoring, debugging, and testing tools.

You can try out these IDEs and see which one works best for you and your project.



## Directory Structure

The project is organized into the following directories and packages:

| Directory/Package | Description |
|-------------------|-------------|
| src/main/java/com/example/app/repository | Contains the repository classes for accessing and managing the resume data in the persistent storage |
| src/main/java/com/example/app/model | Contains the domain model classes, representing the entities and their relationships |
| src/main/java/com/example/app/controller | Contains the controller classes, handling the incoming HTTP requests and mapping them to the appropriate repository methods |
| src/main/java/com/example/app/service | Contains the service classes, implementing the business logic and interacting with the repository classes |
| src/main/java/com/example/app/util | Contains utility classes and helper methods |
| src/main/resources | Configuration files and other resources |
| src/test/java/com/example/app | Unit and integration tests for the project, organized by layer |
| src/test/resources | Test-specific resources, such as test data and configuration files |
| docker | Docker configuration files for running the project with Docker |


## Best Practices

To ensure that your project is well-designed and maintainable, it is important to follow some best practices and avoid common pitfalls. Here are some recommendations based on articles written by [Vlad Mihalcea](https://vladmihalcea.com/) and [Thorben Janssen](https://www.thoughts-on-java.org/):

- [Avoid the N+1 query problem](https://vladmihalcea.com/the-n-plus-1-query-problem/)
- [Choose the right fetch type](https://vladmihalcea.com/jpa-hibernate-fetch-strategies/)
- [Use the right JPA event listeners](https://vladmihalcea.com/jpa-event-listeners/)
- [Understand the difference between CascadeType.ALL and CascadeType.PERSIST](https://www.thoughts-on-java.org/jpa-cascade-type-all-persist-cascadetype-remove/)
- [Avoid the transaction-per-request anti-pattern](https://www.thoughts-on-java.org/transaction-per-request-anti-pattern/)

By following these recommendations, you can improve the performance and maintainability of your project.

