## Backend

Recommended IDE: IntelliJ IDEA
import the backend folder as a maven project in IntelliJ (or any other IDE)

### how to run the backend

You must have maven installed on your machine.
to creat the spring-boot executable file run with maven:
```sh
mvn -N io.takari:maven:wrapper
```

Ensure that the wrapper is executable:
```sh
chmod +x mvnw
```

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
- springdoc-openapi-ui (for OpenAPI documentation)
- hibernate (for ORM)
- junit (for testing)
- mockito (for mocking)
- JWT (for JSON Web Tokens)
- sqlite-jdbc (for database)
- lombok (for less boilerplate code)
- h2 (for databse in-memory testing)