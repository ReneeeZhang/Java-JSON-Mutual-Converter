package edu.duke.ece651.classbuilder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NonArrayFieldBuilderTest {
  @Test
  public void test_getFildMethodsforPrimitiveType() {
    // byte
    test_getFieldMethodsForPrimitiveType("name", "byte");
    // short
    test_getFieldMethodsForPrimitiveType("name", "short");
    // int
    test_getFieldMethodsForPrimitiveType("name", "int");
    // float
    test_getFieldMethodsForPrimitiveType("name", "float");
    // long
    test_getFieldMethodsForPrimitiveType("name", "long");
    // double
    test_getFieldMethodsForPrimitiveType("name", "double");
    // booolean
    test_getFieldMethodsForPrimitiveType("name", "boolean");
    // char
    test_getFieldMethodsForPrimitiveType("name", "char");
  }
  
  private void test_getFieldMethodsForPrimitiveType(String fieldName, String fieldType) {
    FieldBuilder fb = new PrimitiveFieldBuilder(fieldName, fieldType);
    test_getFieldMethodsForNonArrayType(fb, fieldName, fieldType);
  }

  @Test
  public void test_getFieldMethodsForObjectType() {
    test_getFieldMethodsForObjectType("name", "alien");
  }

  private void test_getFieldMethodsForObjectType(String fieldName, String fieldType) {
    FieldBuilder fb = new ObjectFieldBuilder(fieldName, fieldType);
    test_getFieldMethodsForNonArrayType(fb, fieldName, fieldType);
  }

  private void test_getFieldMethodsForNonArrayType(FieldBuilder fb, String fieldName, String fieldType) {
    String getter = "\tpublic " + fieldType + " get" + fb.toCaptalizedName() + "() {\n\t\treturn this." + fieldName
        + ";\n\t}\n";
    String setter = "\tpublic void set" + fb.toCaptalizedName() + "(" + fieldType + " f){\n\t\tthis." + fieldName
        + " = f;\n\t}\n";
    StringBuilder ans = new StringBuilder(getter);
    ans.append('\n').append(setter);
    assertEquals(fb.getFieldMethods(), ans.toString());
  }
}
