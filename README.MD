# JsonStringify

## Description

A library that work like JS's JSON.stringify

## Example

In YourClass.java file:

```java
package yourpackage;

import fr.poco.jsonstringify.JsonStringifiable;
import fr.poco.jsonstringify.JsonItem;

@JsonStringifiable
class YourClass {
  @JsonField private String name; // works with String
  @JsonField private int age; // works with all numbers
  @JsonField private SubClass subClass; // works with sub classes that are stringifiable
  @JsonField private String[] interests; // works with arrays of all types above
  
  @JsonField(key = "custom") // customize key
  private int customKey;
  
  private int notAField; // Will not be stringified

  public YourClass() {
    this.name = "John Doe";
    this.age = 20;
    this.subClass = new SubClass();
    this.interests = {"development", "music"};
    this.customKey = 42;
    this.notAField = 0;
  }
} 
```

In Main.java file:

```java
package yourpackage;

import fr.poco.jsonstringify.JsonStringifier;

class Main {
  public static void main(String[] args) {
    YourClass yourClass = new YourClass();
    JsonStringifier jsonStringifier = new JsonStringifier();
    String jsonString = jsonStringifier.stringify(testClass);
    System.out.println(jsonString);
  }
}
```

And you will see in the console (without whitespace):

```json
{
  "name": "John Doe",
  "age": 20,
  "subClass": {
    ...
  },
  "interests": ["development", "music"],
  "custom": 42
}
```
