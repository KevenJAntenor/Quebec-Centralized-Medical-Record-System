# equipe9-inf5153-h2024-projet

## project description

Hexagonal architecture is used. check [parite1.md](partie1.md) for more details.

## Backend

import the backend folder as a maven project in IntelliJ.
(do not import the root folder)

### TODO how to run the backend

First step is to create databases and tables.
For now we have two databases, one for production and one for testing.

to create the databases and tables :

- cd backend/sql
- chmod u+x deploy-database.sh
- ./deploy-database.sh

this (for now) creates medical_file.db in backend, and an exact db inside of backend/src/test/resources/db

to run spring-boot application, you can use the following commands: (Which open on port 8080, make sure it is not used by another application)
This doesnt do anything for now, but it will start the server.

- ./mvnw clean spring-boot:run

to run the tests (must setup databses before), you can use the following commands:

- ./mvnw clean test

### Java dependencies (managed by Maven) are: INCOMPLETE

- spring-boot (for REST API)
- junit (for testing)
- mockito (for mocking)
- JWT (for JSON Web Tokens)
- sqlite-jdbc (for database)
- database Rider (for testing database)
- sonarqube (for code quality)
- Fuzzing (for black box testing)

### http request codes

- 200 : OK (for GET, PUT, DELETE)
- 201 : Created (for POST)
- 400 : Bad Request (when the request is not valid by client)
- 403 : Forbidden (when the client is not authorized to access the resource)
- 404 : Not Found (when the resource is not found)
- 409 : Conflict (when the resource is already exists)

## Note

sql scripts to be located in the `backend/src/main/resources` folder.

## Frontend

### TODO how to run frontend

- (docker to build frontend container)

### frontend framework

- [Svelte](https://learn.svelte.dev/tutorial/welcome-to-svelte) (frontend)
