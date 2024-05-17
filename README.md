# Birds and Sightings API

[![Java CI with Maven](https://github.com/mateisilviu/CodingExercise/actions/workflows/maven.yml/badge.svg?branch=main)](https://github.com/mateisilviu/CodingExercise/actions/workflows/maven.yml)

The Birds and Sightings API allows users to log and retrieve bird sightings. It provides endpoints to create, read, update, and delete bird and sighting records.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Packages Used](#packages-used)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)
- [License](#license)

### Installation

Follow these steps to set up the project locally:

#### Clone the repository
```bash
git clone [https://github.com/yourusername/yourproject.git](https://github.com/mateisilviu/CodingExercise.git)
```
#### Navigate into the project directory
```bash
cd CodingExercise
```
#### Build the project using Maven Wrapper
```bash
./mvnw clean install
```
### Usage

### Start the application using Maven Wrapper
```bash
./mvnw spring-boot:run
```
### Packages Used

- **Spring Boot**: Framework for building Java-based web applications.
- **Spring Data JPA**: Provides repositories for data access using JPA.
- **Spring Web**: Module for building web applications, including RESTful services.
- **H2 Database**: In-memory database for development and testing.
- **Lombok**: Library to minimize boilerplate code by generating getters, setters, and other methods at compile-time.
- **Swagger/OpenAPI**: Tools for API documentation and testing.
- **JUnit**: Testing framework for Java.
- **Mockito**: Mocking framework for unit tests.

### API Documentation

After application is started, access [https://localhost:8080/swagger-ui/index.html](https://localhost:8080/swagger-ui/index.html)
