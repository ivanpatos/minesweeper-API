# Minesweeper-API

RESTfull API implementation with Spring Boot and PostgreSQL


## Requirements

- java 8 (https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
- maven (https://maven.apache.org/download.cgi)
- postgresql (https://www.postgresql.org/download/)


## Build & run

In order to build the application, run the following command:

```
> mvn clean package
```

Running the app:

```
> java -jar target\minesweeper-API-0.0.1-SNAPSHOT.jar
```

The API will be deployed by default at http://localhost:8085/minesweeper

Database url (+ user and password), server context path and port can be configured by editing "application.properties", located in src/main/resources

## API specification

The following endpoints are available (all requests with header including "Content-Type": "application/json"). Postman collection with request examples available in src/main/resources

Creation of a player:

```
POST	/player
{
    "username":"johnDoe",
    "password":"qwerty123",
    "email":"john.doe@gmail.com"
}
```

Get of a player:

```
GET	/player/{idPlayer}
```

Creation of a game:

```
POST	/game
{
    "playerId":"1",
    "columns":"3",
    "rows":"3",
    "mines":"1"
}
```

Get of a game:

```
GET	/game/{idGame}
```

Reveal a cell:

```
PUT	/game/{idGame}/cell/{idCell}/reveal
```

Flag a cell:

```
PUT	/game/{idGame}/cell/{idCell}/flag
```

Pause the game:

```
PUT	/game/{idGame}/pause
```
