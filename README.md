
# Magic Items D&D 5E REST API with Spring Boot

This is a project that implements a REST API for retrieving, creating, updating, and deleting magic items for the game Dungeons and Dragons 5th edition.

## Technologies

- Java
- Spring Boot
- Spring Security
- JPA
- Maven


## Getting Started

To get started with this project, follow these steps:

Clone the repository to your local machine using the command:

`git clone https://github.com/<username>/<project-name>.git`

Navigate to the project directory using the command:

`cd <project-name>`

Build the project using the command:

`mvn clean install`

Run the project using the command:

`mvn spring-boot:run`

The API will be available at `http://localhost:8080/api/magic-items`.


## Available Endpoints

The following endpoints are available for non-admin users:

`GET /api/magic-items` - Retrieve a list of all magic items.
`GET /api/magic-items/{magicItemIndexName}` - Retrieve a specific magic item by its index name.

The following endpoints are available for admin users:

`GET /api/magic-items/id/{magicItemId}` - Retrieve a specific magic item by its ID.

`POST /api/magic-items` - Create a new magic item. The request body should be a JSON object containing the following fields:
```
{
    "indexName": "armor",
    "itemName": "Armor, +1, +2, or +3",
    "description": [
        "Armor (light, medium, or heavy), rare (+1), very rare (+2), or legendary (+3)",
        "You have a bonus to AC while wearing this armor. The bonus is determined by its rarity."
    ],
    "rarity": "Varies",
    "url": "/api/magic-items/armor",
    "equipmentCategory": {
        "indexName": "armor",
        "categoryName": "Armor"
    },
    "sourceBook": {
        "sourceName": "Dungeon Master’s Guide"
    }
}
```
`PUT /api/magic-items/{magicItemId}` - Update an existing magic item. The request body should be a JSON object containing the fields to update, including the id field if not passed in the endpoint, like the following example:
```
{
    "indexName": "armor",
    "itemName": "Armor, +1, +2, or +3",
    "description": [
        "Armor (light, medium, or heavy), rare (+1), very rare (+2), or legendary (+3)",
        "You have a bonus to AC while wearing this armor. The bonus is determined by its rarity."
    ],
    "rarity": "Varies",
    "url": "/api/magic-items/armor",
    "equipmentCategory": {
        "id" : 1,
        "indexName": "armor",
        "categoryName": "Armor"
    },
    "sourceBook": {
        "id" : 2,
        "sourceName": "Dungeon Master’s Guide"
    }
}

```
`DELETE /api/magic-items/{magicItemId}` - Delete a specific magic item by its ID.
## Additional Information

As this project is still in development and is intended for personal use only, it is mainly about me learning more about Spring Boot and improving my skills.

