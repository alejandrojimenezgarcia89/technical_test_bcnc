# Technical Test BCNC

## Description

This project is a technical test for BCNC.

## Requirements

- Java 17
- Maven 3.6.3 or higher

## Project Structure

The project is divided into several modules following the hexagonal architecture:

- **boot**: Spring Boot startup configuration.
- **domain**: Contains entities and repositories.
- **application**: Contains business logic.
- **infrastructure**: Contains infrastructure configuration and data access.
- **api-rest**: Contains the controllers that expose the REST API generated with OpenAPI.
- **jacoco-report**: Configuration for generating code coverage reports with JaCoCo.

## Installation

Compile the project:
 ```bash
 mvn clean install
 ```

## Execution

To run the application, use the following command:
```bash
mvn spring-boot:run -pl boot
```

## Tests

To run the tests, use the following command:
```bash
mvn test
```

## Generating Coverage Reports

To generate code coverage reports with JaCoCo, use the following command:
```bash
mvn verify
```
After running the command, open the index.html file located in the jacoco-report/target/site directory to view the coverage report.

## API Documentation

The API development follows an ApiFirst approach, where the API specification is defined first using OpenAPI. This ensures that the API is well-documented and that the API contracts are clear and consistent.

To access the API documentation, run the application and navigate to:
```
http://localhost:8080/swagger-ui.html
```
## Used patterns

### DTO Pattern

The DTO (Data Transfer Object) pattern is used to transfer data between the layers of the application. DTOs are simple objects that do not contain business logic and are used to encapsulate the data sent and received through the API.

### Adapter Pattern

The Adapter pattern is used to connect incompatible interfaces. In this project, it is used to connect the infrastructure repositories with the ports defined in the domain. An example of this is the PriceRepositoryAdapter class, which adapts the JdbcPriceRepository to implement the PriceRepository interface.

## License

This project is licensed under the MIT License. See the `LICENSE` file for more details
