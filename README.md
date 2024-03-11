# equipe9-inf5153-h2024-projet

## project description

Hexagonal architecture is used. check [parite1.md](partie1.md) for more details.



## Backend

import the backend folder as a maven project in IntelliJ.
(do not import the root folder as a maven project)

TODO how to run the backend

Technologies for backend are:

- Java 17 (backend)
- [SQLite3](https://www.sqlite.org/index.html) (database)

Java dependencies (managed by Maven) are: INCOMPLETE

- spring-boot (for REST API)
- junit (for testing)
- JWT (for JSON Web Tokens)

http request codes : NOT COMPLETE 

- 200 : OK (for GET, PUT, DELETE)
- 201 : Created (for POST)

- 400 : Bad Request (when the request is not valid by client)
- 403 : Forbidden (when the client is not authorized to access the resource)
- 404 : Not Found (when the resource is not found)


## Frontend

TODO how to contribute to the frontend

Technologies for frontend 

- [Svelte](https://learn.svelte.dev/tutorial/welcome-to-svelte) (frontend)

## Database

sql scripts is located in the `backend/src/main/resources` folder.

## How to contribute

### 1. Create a local branch

```bash
git branch new-branch-name
git checkout new-branch-name
```

### 2. Add and commit your changes

```bash
git add file1 file2
git commit -m "Your commit message"
```

### 3. Push your changes to the remote repository

```bash
git push origin new-branch-name
```

### 4. Create a merge request

- Go to the repository on GitLab
- Click on the `Merge Request` tab
- Click on the `New merge request` button
- Add convinient title and description
- Add all members of the team as reviewers
- Click on the `Submit merge request` button

Please don't merge your own merge request. Wait for at least two of your team members approval before merging the merge request.



