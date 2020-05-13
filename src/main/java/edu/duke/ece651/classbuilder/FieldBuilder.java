package edu.duke.ece651.classbuilder;

public abstract class FieldBuilder {
  private static final String ARRAY_SIGN = "ArrayList<";

  protected String fieldName;
  protected String fieldType;

  public FieldBuilder(String fieldName, String fieldType) {
    this.fieldName = fieldName;
    this.fieldType = fieldType;
  }

  public String getFieldName() {
    return fieldName;
  }

  public String getFieldType() {
    return fieldType;
  }

  public boolean isArray() {
    return fieldType.indexOf(ARRAY_SIGN) == 0;
  }

  public String toCaptalizedName() {
    // Begins with lower case letter
    if (fieldName.charAt(0) >= 97 && fieldName.charAt(0) <= 122) {
      StringBuilder capFieldName = new StringBuilder(fieldName);
      capFieldName.setCharAt(0, (char) (capFieldName.charAt(0) + 'A' - 'a'));
      return capFieldName.toString();
    }
    // Otherwise, return directly
    return fieldName;
  }

  protected boolean isPrimitive(String type) {
    String[] primitives = { "boolean", "byte", "char", "short", "int", "long", "float", "double" };
    for (int i = 0; i < primitives.length; i++) {
      if (type.equals(primitives[i])) {
        return true;
      }
    }
    return false;
  }

  public abstract String getFieldMethods();

  public abstract String getSerializationCode();

  public abstract String getDeserializationCode();
}

