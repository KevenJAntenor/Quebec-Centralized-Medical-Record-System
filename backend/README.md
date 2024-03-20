## Backend

Recommended IDE: IntelliJ IDEA
import the backend folder as a maven project in IntelliJ (or any other IDE)

### how to run the backend

to start spring-boot application:
```sh
./mvnw clean spring-boot:run
```

tp run the tests:
```sh
./mvnw clean verify
```

database is created automatically when running spring-boot application.
one sqlite database is used for production, and an h2 database, is used for testing.

### Java dependencies (managed by Maven) are: 

- spring-boot (for REST API)
- junit (for testing)
- mockito (for mocking)
- JWT (for JSON Web Tokens)
- sqlite-jdbc (for database)
- database Rider (for testing database)
- sonarqube (for code quality)
- Fuzzing (for black box testing)
