# junit-source-launcher

Starting with Java 25 it is possible to write minimal source code test programs using the `org.junit.start` module.
For example, take a look at the [HelloTests.java](src/HelloTests.java) file reading:

```java
import module org.junit.start;

void main() {
    JUnit.run();
}

@Test
void stringLength() {
    Assertions.assertEquals(11, "Hello JUnit".length());
}
```

Download `org.junit.start` module and its transitively required modules into a local `lib/` directory by running in a shell:

```shell
java lib/DownloadRequiredModules.java
```

With all required modular JAR files available in a local `lib/` directory, the following Java command will discover and execute tests using the JUnit Platform.

```shell
java --module-path lib --add-modules org.junit.start src/HelloTests.java
```

It will also print the result tree to the console.

```text
╷
└─ JUnit Jupiter ✔
   └─ HelloTests ✔
      └─ stringLength() ✔
```

# junit-source-launcher

Starting with Java 25 it is possible to write minimal source code test programs using the `org.junit.start` module.
For example, take a look at the [HelloTests.java](src/HelloTests.java) file reading:

```java
import module org.junit.start;

void main() {
    JUnit.run();
}

@Test
void stringLength() {
    Assertions.assertEquals(11, "Hello JUnit".length());
}
```

Download `org.junit.start` module and its transitively required modules into a local `lib/` directory by running in a shell:

```shell
java lib/DownloadRequiredModules.java
```

With all required modular JAR files available in a local `lib/` directory, the following Java command will discover and execute tests using the JUnit Platform.

```shell
java --module-path lib --add-modules org.junit.start src/HelloTests.java
```

It will also print the result tree to the console.

```text
╷
└─ JUnit Jupiter ✔
   └─ HelloTests ✔
      └─ stringLength() ✔
```

## Open Test Reporting output

JUnit Platform reporting is enabled for this example via `src/junit-platform.properties`.
After running the command above, an Open Test Reporting XML file is written under:

```
out/junit-<uniqueNumber>/open-test-reporting.xml
```

Note: multiple `out/junit-*` directories can exist after several runs; the latest one contains the newest report.