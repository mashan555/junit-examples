# JUNIE.md

**Programming language:** Java 17

**Primary package manager/build tool:** Maven (via Maven Wrapper)

---

## Install the environment

```sh
cd junit-jupiter-starter-maven
./mvnw dependency:resolve -B
```

## Activate the environment

_No manual activation is required; Maven uses your system JDK 17 and local repository._

## Run tests

```sh
cd junit-jupiter-starter-maven
./mvnw test -B
```
