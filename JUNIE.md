# Project Environment Setup for junit-jupiter-starter-maven

- **Programming Language:** Java 17
- **Primary Package Manager/Build Tool:** Maven (using Maven Wrapper)

## Environment installation script
```sh
# Ensure you are in the module's directory
cd /home/runner/work/junit-examples/junit-examples/junit-jupiter-starter-maven

# Ensure Maven Wrapper is executable (run once if needed)
chmod +x mvnw

# Download and install all dependencies (non-interactive)
./mvnw dependency:resolve
```

## Environment activation command
No activation is required; simply ensure you run commands within the module directory.

## Command to run tests
```sh
./mvnw test -DskipITs
```
- This runs all unit tests and skips any integration tests (if present).
