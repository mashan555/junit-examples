# junit-jupiter-starter-maven

The `junit-jupiter-starter-maven` project demonstrates how to execute JUnit Jupiter
tests using Maven.

Please note that this project uses the [Maven Wrapper](https://github.com/apache/maven-wrapper).
Thus, to ensure that the correct version of Maven is used, invoke `mvnw` instead of `mvn`.

### HelloWorld example

This module includes a simple `HelloWorld` class and a test.

- Source: `src/main/java/com/example/project/HelloWorld.java`
- Test: `src/test/java/com/example/project/HelloWorldTests.java`

Usage:

```java
import com.example.project.HelloWorld;

public class Demo {
  public static void main(String[] args) {
    System.out.println(new HelloWorld().hello()); // Hello, World!
  }
}
```
