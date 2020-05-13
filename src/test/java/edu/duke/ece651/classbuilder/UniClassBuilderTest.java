package edu.duke.ece651.classbuilder;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class UniClassBuilderTest {
  @Test
  public void test_getUniClassNameAndFields() {
    // Test a  class without fields
    test_getUniClassNameAndFields("{\'name\' : \'People\'}", "People");

    // Test a class with two fields, one is a non-array type; the other is an array type
    test_getUniClassNameAndFields(
        "{\'name\' : \'People\', \'fields\' : [{\'name\' : \'ID\', \'type\' : \'int\'}, {\'name\' : \'pets\', \'type\' : {\'e\' : {\'e\' : \'char\'}}}]}",
        "People");

    // Test exception - no 'name' -> has 'names' instead
    assertThrows(JSONException.class, ()->{    test_getUniClassNameAndFields(
        "{\'names\' : \'People\', \'fields\' : [{\'name\' : \'ID\', \'type\' : \'int\'}, {\'name\' : \'pets\', \'type\' : {\'e\' : {\'e\' : \'Pet\'}}}]}",
        "People");
      });
  }

  private void test_getUniClassNameAndFields(String str, String trueName) {
    JSONObject c = new JSONObject(str);
    UniClassBuilder ucb = new UniClassBuilder(c);
    assertEquals(ucb.getUniClassName(), trueName);
    // FieldBuilder[] fb = ucb.getFields();
    // if (fieldNames == null && fieldTypes == null) {
    //   assertEquals(fb, null);
    // }
    // else {
    //   assertEquals(fieldNames.length, fb.length);
    //   for (int i = 0; i < fb.length; i++) {
    //     assertEquals(fb[i].getFieldName(), fieldNames[i]);
    //     assertEquals(fb[i].getFieldType(), fieldTypes[i]);
    //   }
    // }
  }
}
