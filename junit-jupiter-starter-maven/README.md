# junit-jupiter-starter-maven

The `junit-jupiter-starter-maven` project demonstrates how to execute JUnit Jupiter
tests using Maven.

Please note that this project uses the [Maven Wrapper](https://github.com/apache/maven-wrapper).
Thus, to ensure that the correct version of Maven is used, invoke `mvnw` instead of `mvn`.

## HelloWorld example

This module includes a simple `HelloWorld` API at `com.example.project.HelloWorld` with a static method `hello()` that returns `"Hello, World!"`.

Example usage:

```java
import com.example.project.HelloWorld;

public class Demo {
    public static void main(String[] args) {
        System.out.println(HelloWorld.hello());
    }
}
```
