
# Magic Items D&D 5E API

This is a project that implements a REST API for retrieving, creating, updating, and deleting magic items for the game Dungeons and Dragons 5th edition.

## Topics
- [Technologies](https://github.com/jawwadbr/dnd5e-magicItems-api#technologies)
- [How to run](https://github.com/jawwadbr/dnd5e-magicItems-api#how-to-run)
    * [Locally](https://github.com/jawwadbr/dnd5e-magicItems-api#locally)
- [API Endpoints](https://github.com/jawwadbr/dnd5e-magicItems-api#api-endpoints)
- [Additional Information](https://github.com/jawwadbr/dnd5e-magicItems-api#additional-information)

## Technologies

- [Java 17](https://docs.oracle.com/en/java/javase/17/)
- [Spring Boot v3.1.1](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#repositories)
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

## API Endpoints

To make the HTTP request below just for testing, [Postman](https://www.postman.com) tool was used:

### Public Endpoints

#### Magic Items Endpoints
- GET /api/magic-items?page=?&pageSize=?&sortBy=? (Parameters are optional, you can remove if needed)
- GET /api/magic-items/{indexName}

##### Equipment Categories Endpoints
- GET /api/equipment-categories?page=?&pageSize=?&sortBy=? (Parameters are optional, you can remove if needed)
- GET /api/equipment-categories/{indexName}

##### Source Books Endpoints
- GET /api/source-books?page=?&pageSize=?&sortBy=? (Parameters are optional, you can remove if needed)
- GET /api/source-books/{indexName}

### Authenticated Endpoints (SECURITY NEED IMPLEMENTATION, ALL ENDPOINTS BELOW ARE AVAILABLE TO ALL)
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

## Additional Information

As this project is still in development and is intended for personal use only, it is mainly about me learning more about Spring Boot and improving my skills.

