# Project Environment Summary

- Programming language: Java 17
- Primary package manager: Maven (via Maven Wrapper)

## Setup script
```
# Ensure Maven Wrapper is executable
chmod +x ./mvnw
# Install all dependencies
./mvnw dependency:resolve --batch-mode
```

## Activate environment
No special activation required. Just ensure Java 17 is available and use the Maven Wrapper (`./mvnw`).

## Run tests
```
./mvnw test --batch-mode
```
