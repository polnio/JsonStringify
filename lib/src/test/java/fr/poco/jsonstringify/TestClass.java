package fr.poco.jsonstringify;

@JsonStringifiable
class TestClass {
  @JsonStringifiable
  class SubClass {
    @JsonField
    private String name = "coco";
  }

  @JsonField
  private String firstName;
  @JsonField
  private String lastName;
  @JsonField(key = "test")
  private String testField;
  @JsonField
  private int age;
  @JsonField(key = "sub")
  private SubClass subClass;

  public TestClass() {
    super();
    firstName = "Jean";
    lastName = "Dupont";
    testField = "A Test";
    age = 35;
    subClass = new SubClass();
  }
}
