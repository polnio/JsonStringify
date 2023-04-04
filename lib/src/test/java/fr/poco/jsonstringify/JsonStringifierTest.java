package fr.poco.jsonstringify;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class JsonStringifierTest {
  @Test
  public void test() throws Exception {
    TestClass testClass = new TestClass();
    JsonStringifier jsonStringifier = new JsonStringifier();
    String jsonString = jsonStringifier.stringify(testClass);
    assertEquals(jsonString,
        "{\"firstName\":\"Jean\",\"lastName\":\"Dupont\",\"sub\":{\"name\":\"coco\"},\"test\":\"A Test\",\"age\":35}");
  }
}
