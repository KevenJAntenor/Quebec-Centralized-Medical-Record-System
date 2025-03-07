## Table of Contents

1. [Dependencies](#dependencies)
2. [How to run the backend](#how-to-run-the-backend)
3. [Important notes about the database](#important-notes-about-the-database)
4. [OpenAPI documentation](#openapi-documentation)
5. [Jacoco test coverage report](#jacoco-test-coverage-report)

## Backend

### Dependencies

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi) (v3.9.6)
- [SQLite3](https://www.sqlite.org/download.html)

### How to run the backend

Inside the backend folder, run the following command to build the project.
This will also run the tests and generate the test coverage report.
```sh
mvn clean install
```

then you can start spring-boot application:
```sh
mvn spring-boot:run
```

You can verify that the backend is running by accessing the following url in your browser: [http://localhost:8080](http://localhost:8080).

You will see a message saying `Error: Full authentication is required to access this resource`, that's normal, as all endpoints are secured.

The only paths that are accessable in the backend are the documentation [swagger-ui](http://localhost:8080/swagger-ui/index.html), and the test coverage report [jacoco](http://localhost:8080/jacoco/index.html).

While the backend is running, you can now run the frontend. [frontend README](../frontend/README.md)

to run the tests only:
```sh
mvn clean verify
```

### Important notes about the database

The database is created automatically when running the spring-boot application, this is possible thanks to the use of hibernate and JPA dependency.

**No need to execute any SQL script to create the database. nor create the tables, nor insert the data. Everything is done automatically. However, the SQLITE3 script is present at backend/db.sql**

The main database is an sqlite database, which is created in the root folder of the backend, "medical_file.db".

Currently, the database is created with a `create-drop` strategy, which means that the database is created when the application starts and is destroyed when the application stops. This is useful for development, but not for production.

For our integration tests, we use an in-memory database, h2, which is a separate database from the main database. This is useful for testing, as it allows us to test the application without affecting the main database.

### OpenAPI documentation

The OpenAPI documentation is available at [swagger-ui](http://localhost:8080/swagger-ui/index.html).
Must be running the backend to access the OpenAPI documentation.

### Jacoco test coverage report

The test coverage report is available at [jacoco](http://localhost:8080/jacoco/index.html).
Must be running the backend to access the test coverage report.
Alternatively, you can open the index.html file in the target/site/jacoco folder.
Make sure to run the tests before accessing the test coverage report.
