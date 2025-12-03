# Environment & Testing Guide for junit-jupiter-starter-maven

- **Programming Language:** Java 17
- **Primary Package Manager:** Maven (via Maven Wrapper `./mvnw`)

## Install Dependencies & Prepare Environment
```bash
./mvnw dependency:resolve
```

## Activate Environment
No additional environment activation required. (Java & Maven handled by system and Maven Wrapper.)

## Run Tests
```bash
./mvnw test
```
