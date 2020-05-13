package edu.duke.ece651.classbuilder;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class is to classify three types of Java fields:
 * 1. Primitive (including String)
 * 2. Object
 * 3. Array
 * And generate corresponding {@link FieldBuilder} when constructing given a {@link JSONObject} which describes a Java field type
 */
public class FieldClassifier {
  private static final String FIELD_NAME_TAG = "name";
  private static final String FIELD_TYPE_TAG = "type";
  private static final String ARRAY_TAG = "e";
  
  private FieldBuilder fb;

  public FieldClassifier(JSONObject oneField) {
    String fieldName = oneField.getString(FIELD_NAME_TAG);
    String fieldType = new String();
    try {
      fieldType = oneField.getString(FIELD_TYPE_TAG);
    } catch (JSONException ex) { // Meaning it is an array type
      JSONObject fieldTypeObj = oneField.getJSONObject(FIELD_TYPE_TAG);
      StringBuilder fieldTypeBuilder = new StringBuilder();
      generateArray(fieldTypeObj, fieldTypeBuilder, true);
      fieldType = fieldTypeBuilder.toString();
      this.fb = new ArrayFieldBuilder(fieldName, fieldType);
      return;
    }
    
    // Non-array type
    if (isPrimitiveOrString(fieldType)) { // Primitive type
      this.fb = new PrimitiveFieldBuilder(fieldName, fieldType);
    } else {  // Object type
      this.fb = new ObjectFieldBuilder(fieldName, fieldType);
    }
  }
  
  public FieldBuilder classify() {
    return fb;
  }

  private void generateArray(JSONObject arr, StringBuilder fieldTypeBuilder, boolean isFirst) {
    JSONObject level = arr.optJSONObject(ARRAY_TAG);
    if (isFirst) {
      fieldTypeBuilder.append("ArrayList<");
    } else {
      fieldTypeBuilder.append("Collection<");
    }
    if (level == null) { // To the inner-most layer of the array
      String innerType = arr.getString(ARRAY_TAG);
      if (isPrimitive(innerType)) {
        innerType = changeFromPrimitive(innerType);
      }
      fieldTypeBuilder.append(innerType);
    } else {
      generateArray(level, fieldTypeBuilder, false);
    }
    fieldTypeBuilder.append('>');
  }

  private boolean isPrimitive(String type) {
    String[] primitives = { "boolean", "byte", "char", "short", "int", "long", "float", "double" };
    for (int i = 0; i < primitives.length; i++) {
      if (type.equals(primitives[i])) {
        return true;
      }
    }
    return false;
  }

  private boolean isPrimitiveOrString(String fieldType) {
    return isPrimitive(fieldType) || fieldType.equals("String");
  }
  
  private String changeFromPrimitive(String innerType) {
    if (innerType.equals("int")) {
      return "Integer";
    } else if (innerType.equals("char")) {
      return "Character";
    } else {
      StringBuilder innerTypeBuilder = new StringBuilder(innerType);
      innerTypeBuilder.setCharAt(0, (char) (innerTypeBuilder.charAt(0) + 'A' - 'a'));
      return innerTypeBuilder.toString();
    }
  }

}
