# Magic Items D&D 5E API

This is a project that implements a REST API for retrieving, creating, updating, and deleting magic items for the game
Dungeons and Dragons 5th edition.

## Topics

- [Technologies](https://github.com/jawwadbr/dnd5e-magicItems-api#technologies)
- [How to run](https://github.com/jawwadbr/dnd5e-magicItems-api#how-to-run)
    * [Locally](https://github.com/jawwadbr/dnd5e-magicItems-api#locally)
    * [Remotely](https://github.com/jawwadbr/dnd5e-magicItems-api#remotely)
- [API Endpoints](https://github.com/jawwadbr/dnd5e-magicItems-api#api-endpoints)
    * [Public Endpoints](https://github.com/jawwadbr/dnd5e-magicItems-api#magic-items-endpoints)
      * [Authentication Endpoints](https://github.com/jawwadbr/dnd5e-magicItems-api#authentication-endpoints)
      * [Magic Items Endpoints](https://github.com/jawwadbr/dnd5e-magicItems-api#magic-items-endpoints)
      * [Equipment Categories Endpoints](https://github.com/jawwadbr/dnd5e-magicItems-api#equipment-categories-endpoints)
      * [Source Books Endpoints](https://github.com/jawwadbr/dnd5e-magicItems-api#source-books-endpoints)
    * [Authenticated Endpoints](https://github.com/jawwadbr/dnd5e-magicItems-api#authenticated-endpoints)
      * [Magic Items Endpoints](https://github.com/jawwadbr/dnd5e-magicItems-api#magic-items-endpoints-1)
      * [Equipment Categories Endpoints](https://github.com/jawwadbr/dnd5e-magicItems-api#equipment-categories-endpoints-1)
      * [Source Books Endpoints](https://github.com/jawwadbr/dnd5e-magicItems-api#source-books-endpoints-1)
      * [User Endpoints](https://github.com/jawwadbr/dnd5e-magicItems-api#user-endpoints)
- [Additional Information](https://github.com/jawwadbr/dnd5e-magicItems-api#additional-information)

## Technologies

- [Java 17](https://docs.oracle.com/en/java/javase/17/)
- [Spring Boot v3.1.1](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#repositories)
- [Spring Security](https://docs.spring.io/spring-security/reference/index.html)
- [JSON Web Token](https://jwt.io/introduction)
- [Lombok](https://projectlombok.org/features/)
- [Slugify](https://github.com/slugify/slugify)
- [MySQL](https://dev.mysql.com/doc/)

## How to run

### Locally

- Clone the repository git
- Build the project:

```
./mvnw clean package
```

- Execute project:

```
java -jar target/dnd5e-api-0.0.1-SNAPSHOT.jar
```

The API will be available at [localhost:8080](http://localhost:8080).

### Remotely
[![Uptime Robot status](https://img.shields.io/uptimerobot/status/m795208943-9f8534bbaa41a8e3fe311c61.svg)](https://stats.uptimerobot.com/rGj26FwD08)

The API is temporarily available at [18.229.31.108:8080](http://18.229.31.108:8080/api/magic-items) using [AWS (Amazon Web Services)](https://aws.amazon.com/pt/?nc2=h_lg).

## API Endpoints

To make the HTTP request below, [Postman](https://www.postman.com) tool was used:

### Public Endpoints

#### Authentication Endpoints

Use this endpoint to authenticate and get a Access Token for the [Authenticated Endpoints](https://github.com/jawwadbr/dnd5e-magicItems-api#authenticated-endpoints). Type of the authentication is Bearer Token.

- POST /api/auth/login
```
{
    "username": "admin",
    "password": "admin"
}

# Response

{
    "accessToken": "<TheAccessToken>"
}
```
- POST /api/auth/register (By default, when a user is created, their role is set as "User")
```
{
    "username": "user",
    "password": "user"
}
```

#### Magic Items Endpoints

- GET /api/magic-items?page=?&pageSize=?&sortBy=? (The parameters are optional and can be omitted if not required)
- GET /api/magic-items/{indexName}

##### Equipment Categories Endpoints

- GET /api/equipment-categories?page=?&pageSize=?&sortBy=? (The parameters are optional and can be omitted if not required)
- GET /api/equipment-categories/{indexName}

##### Source Books Endpoints

- GET /api/source-books?page=?&pageSize=?&sortBy=? (The parameters are optional and can be omitted if not required)
- GET /api/source-books/{indexName}

### Authenticated Endpoints

#### Magic Items Endpoints

- GET /api/magic-items/id/{id}
- POST /api/magic-items

```
{
    "itemName": "The Item Name",
    "topDescr": "The top description. Armor (light, medium, or heavy), rare (+1), very rare (+2), or legendary (+3)",
    "descr": "The long description.",
    "rarity": "Varies",
    "equipmentName": "Armor",
    "sourceName": "Dungeon Master’s Guide"
}
```

- PATCH /api/magic-items/{id}

```
{
    "itemName": "The New Updated Item Name",
    "topDescr": "The New Updated top description. Armor (light, medium, or heavy), rare (+1), very rare (+2), or legendary (+3)",
    "descr": "The New Updated long description.",
    "rarity": "Varies",
    "equipmentName": "Armor",
    "sourceName": "Players Handbook"
}
```

- DELETE /api/magic-items/{id}

##### Equipment Categories Endpoints

- GET /api/equipment-categories/id/{id}
- POST /api/equipment-categories

```
{
    "equipmentName": "The Equipment Category"
}
```

- PATCH /api/equipment-categories/{id}

```
{
    "equipmentName": "The New Updated Equipment Category"
}
```

- DELETE /api/equipment-categories/{id}

##### Source Books Endpoints

- GET /api/source-books/id/{id}
- POST /api/source-books

```
{
    "sourceName": "Book of the Dead"
}
```

- PATCH /api/source-books/{id}

```
{
    "sourceName": "Book of the Dead"
}
```

- DELETE /api/source-books/{id}

##### User Endpoints

- GET /api/user?page=?&pageSize=?&sortBy=? (The parameters are optional and can be omitted if not required)
- GET /api/user/{username}
- DELETE /api/user/{username}
- POST /api/user/{username}/activation (These endpoint deactivate or activate a User)

## Additional Information

As this project is still in development and is intended for personal use only, it is mainly about me learning more about
Spring Boot and improving my skills.

