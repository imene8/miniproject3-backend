# chaussures-backend

Spring Boot 3.3.5 REST API for the Mini Project 3 shoe-management application.

## Stack

- Java 17 target (build verified with JDK 27)
- Spring Web, Spring Data JPA/Hibernate, Validation
- Spring Security 6 with stateless JWT (jjwt 0.12.6)
- MySQL 8 (`chaussures_db`)

## Run

```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-27"
.\mvnw.cmd -B test
.\mvnw.cmd -B -DskipTests clean package
java -jar .\target\chaussures-backend-0.0.1-SNAPSHOT.jar
```

Base URL: `http://localhost:8090/chaussures-api/api`

## Demo accounts

- `admin` / `123` → `ROLE_ADMIN`
- `imene` / `123` → `ROLE_USER`

## Documentation

See `../documentation/API_DOCUMENTATION.md`, `../documentation/DATABASE_DESIGN.md` and `../documentation/SETUP_GUIDE.md`.

No GitHub push is performed by the project setup.
