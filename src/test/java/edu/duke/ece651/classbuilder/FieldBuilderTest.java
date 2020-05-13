package edu.duke.ece651.classbuilder;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class FieldBuilderTest {
  @Test
  public void test_simpleJsonNameAndType() {
    // Test a regular example
    test_getFieldNameAndType("{\'name\' : \'person\', 'type' : \'String\'}", "person", "String");
  }

  @Test
  public void test_multiArrayNameAndType() {
    // Test with a multi-array
    test_getFieldNameAndType("{\'name\' : \'wallet\', 'type' : {\'e\' : {\'e\' : {\'e\' : \'Money\'}}}}", "wallet",
        "ArrayList<Collection<Collection<Money>>>");
  }

  @Test
  public void test_intNameAndType() {
    // Test primitive types - int
    test_getFieldNameAndType("{\'name\' : \'wallet\', 'type' : {\'e\' : {\'e\' : {\'e\' : \'int\'}}}}", "wallet",
        "ArrayList<Collection<Collection<Integer>>>");
  }

  @Test
  public void test_longNameAndType() {
    // Test primitive types - long
    test_getFieldNameAndType("{\'name\' : \'wallet\', 'type' : {\'e\' : {\'e\' : {\'e\' : \'long\'}}}}", "wallet",
        "ArrayList<Collection<Collection<Long>>>");
  }

  @Test
  public void test_doubleNameAndType() {
    // Test primitive types - double
    test_getFieldNameAndType("{\'name\' : \'wallet\', 'type' : {\'e\' : {\'e\' : {\'e\' : \'double\'}}}}", "wallet",
        "ArrayList<Collection<Collection<Double>>>");
  }

  @Test
  public void test_byteNameAndType() {
    // Test primitive types - byte
    test_getFieldNameAndType("{\'name\' : \'wallet\', 'type' : {\'e\' : {\'e\' : {\'e\' : \'byte'}}}}", "wallet",
        "ArrayList<Collection<Collection<Byte>>>");
  }

  @Test
  public void test_booleanNameAndType() {
    // Test primitive types - boolean
    test_getFieldNameAndType("{\'name\' : \'wallet\', 'type' : {\'e\' : {\'e\' : {\'e\' : \'boolean\'}}}}", "wallet",
        "ArrayList<Collection<Collection<Boolean>>>");
  }

  @Test
  public void test_floatNameAndType() {
    // Test primitive types - float
    test_getFieldNameAndType("{\'name\' : \'wallet\', 'type' : {\'e\' : {\'e\' : {\'e\' : \'float\'}}}}", "wallet",
        "ArrayList<Collection<Collection<Float>>>");
  }

  @Test
  public void test_charNameAndType() {
    // Test primitive types - char
    test_getFieldNameAndType("{\'name\' : \'wallet\', 'type' : {\'e\' : {\'e\' : {\'e\' : \'char\'}}}}", "wallet",
        "ArrayList<Collection<Collection<Character>>>");
  }

  @Test
  public void test_shortNameAndType() {
    // Test primitive types - short
    test_getFieldNameAndType("{\'name\' : \'wallet\', 'type' : {\'e\' : {\'e\' : {\'e\' : \'short\'}}}}", "wallet",
        "ArrayList<Collection<Collection<Short>>>");
  }

  @Test
  public void test_getFieldNameAndType_noNameExpn() {
    // Test exceptions - no 'name'
    assertThrows(JSONException.class, () -> {
      test_getFieldNameAndType("{\'names\' : \'person\', 'type' : \'String\'}", "person", "String");
    });
  }

  @Test
  public void test_getFieldNameAndType_noTypeExpn() {
    // Test exceptions - no 'type'
    assertThrows(JSONException.class, () -> {
      test_getFieldNameAndType("{\'name\' : \'person\', 'types' : \'String\'}", "person", "String");
    });
  }

  private void test_getFieldNameAndType(String str, String trueName, String trueType) {
    JSONObject f = new JSONObject(str);
    FieldClassifier fc = new FieldClassifier(f);
    FieldBuilder fb = fc.classify();
    assertEquals(fb.getFieldName(), trueName);
    assertEquals(fb.getFieldType(), trueType);
  }

    @Test
  public void test_isArray_true() {
    FieldBuilder fb = generateFieldBuilder("{\'name\' : \'wallet\', 'type' : {\'e\' : {\'e\' : {\'e\' : \'short\'}}}}");
    assertTrue(fb.isArray());
  }

  @Test
  public void test_isArray_false() {
    FieldBuilder fb = generateFieldBuilder("{\'name\' : \'person\', 'type' : \'String\'}");
    assertFalse(fb.isArray());
  }

  @Test
  public void test_toCaptalizedName_canBeCaptalized() {
    FieldBuilder fb = generateFieldBuilder("{\'name\' : \'person\', 'type' : \'String\'}");
    assertEquals(fb.toCaptalizedName(), "Person");
  }

  @Test
  public void test_toCaptalizedName_canNotBeCaptalized() {
    FieldBuilder fb = generateFieldBuilder("{\'name\' : \'_person\', 'type' : \'String\'}");
    assertEquals(fb.toCaptalizedName(), "_person");
  }
  
  private FieldBuilder generateFieldBuilder(String str) {
    JSONObject f = new JSONObject(str);
    FieldClassifier fc = new FieldClassifier(f);
    return fc.classify();
  }
}
