# equipe9-inf5153-h2024-projet

## project description

Hexagonal architecture is used. check [parite1.md](partie1.md) for more details.

## Backend

import the backend folder as a maven project in IntelliJ.
(do not import the root folder)

### TODO how to run the backend

- (docker to build database container)
- mvn spring-boot:run

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
