package edu.duke.ece651.classbuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class ArrayFieldBuidlerTest {
    @Test
  public void test_getFieldMethodsForArrayType_1D() {
    // short
    test_getFieldMethodsForArrayType("{\'name\' : \'wallet\', 'type' : {\'e\' : \'short\'}}", "short");
    // int
    test_getFieldMethodsForArrayType("{\'name\' : \'wallet\', 'type' : {\'e\' : \'int\'}}", "int");
    // char
    test_getFieldMethodsForArrayType("{\'name\' : \'wallet\', 'type' : {\'e\' : \'char\'}}", "char");
    // byte
    test_getFieldMethodsForArrayType("{\'name\' : \'wallet\', 'type' : {\'e\' : \'byte\'}}", "byte");
    // long
    test_getFieldMethodsForArrayType("{\'name\' : \'wallet\', 'type' : {\'e\' : \'long\'}}", "long");
    // float
    test_getFieldMethodsForArrayType("{\'name\' : \'wallet\', 'type' : {\'e\' : \'float\'}}", "float");
    // double
    test_getFieldMethodsForArrayType("{\'name\' : \'wallet\', 'type' : {\'e\' : \'double\'}}", "double");
    // boolean
    test_getFieldMethodsForArrayType("{\'name\' : \'wallet\', 'type' : {\'e\' : \'boolean\'}}", "boolean");
  }

  @Test
  public void test_getFieldMethodsForArrayType_MD() {
    // Character
    test_getFieldMethodsForArrayType("{\'name\' : \'wallet\', 'type' : {\'e\' : {\'e\' : {\'e\' : \'char\'}}}}",
        "Collection<Collection<Character>>");
    // Short
    test_getFieldMethodsForArrayType("{\'name\' : \'wallet\', 'type' : {\'e\' : {\'e\' : {\'e\' : \'short\'}}}}",
        "Collection<Collection<Short>>");
    // Integer
    test_getFieldMethodsForArrayType("{\'name\' : \'wallet\', 'type' : {\'e\' : {\'e\' : {\'e\' : \'int\'}}}}",
        "Collection<Collection<Integer>>");
    // Long
    test_getFieldMethodsForArrayType("{\'name\' : \'wallet\', 'type' : {\'e\' : {\'e\' : {\'e\' : \'long\'}}}}",
        "Collection<Collection<Long>>");
    // Double
    test_getFieldMethodsForArrayType("{\'name\' : \'wallet\', 'type' : {\'e\' : {\'e\' : {\'e\' : \'double\'}}}}",
        "Collection<Collection<Double>>");
    // Float
    test_getFieldMethodsForArrayType("{\'name\' : \'wallet\', 'type' : {\'e\' : {\'e\' : {\'e\' : \'float\'}}}}",
        "Collection<Collection<Float>>");
    // Byte
    test_getFieldMethodsForArrayType("{\'name\' : \'wallet\', 'type' : {\'e\' : {\'e\' : {\'e\' : \'byte\'}}}}",
        "Collection<Collection<Byte>>");
    // Boolean
    test_getFieldMethodsForArrayType("{\'name\' : \'wallet\', 'type' : {\'e\' : {\'e\' : {\'e\' : \'boolean\'}}}}",
        "Collection<Collection<Boolean>>");
    // Other class
    test_getFieldMethodsForArrayType("{\'name\' : \'wallet\', 'type' : {\'e\' : {\'e\' : {\'e\' : \'Money\'}}}}",
        "Collection<Collection<Money>>");
  }

  private void test_getFieldMethodsForArrayType(String str, String innerArrayType) {
    JSONObject f = new JSONObject(str);
    FieldClassifier fc = new FieldClassifier(f);
    FieldBuilder fb = fc.classify();
    String getter = "\tpublic " + innerArrayType + " getWallet(int index) {\n\t\treturn this.wallet.get(index);\n\t}\n";
    String setter = "\tpublic void setWallet(int index, " + innerArrayType
        + " f) {\n\t\tthis.wallet.set(index, f);\n\t}\n";
    String adder = "\tpublic void addWallet(" + innerArrayType + " f) {\n\t\tthis.wallet.add(f);\n\t}\n";
    String numer = "\tpublic int numWallet() {\n\t\treturn this.wallet.size();\n\t}\n";
    StringBuilder ans = new StringBuilder();
    ans.append(getter).append('\n').append(setter).append('\n').append(adder).append('\n').append(numer);
    assertEquals(ans.toString(), fb.getFieldMethods());
  }


  public void test_getSerializationCode_1DPrimitive() {
    FieldBuilder fb = new ArrayFieldBuilder("example", "ArrayList<Integer>");
    String ans = "jsobj = new JSONObject();\n\t\tarr = new JSONArray(this." + fb.getFieldName()
        + ");\n\t\tjsobj.put(\"" + fb.getFieldName() + "\", arr);\n\t\tvalues.put(jsobj);\n\t\t";
    assertEquals(ans, fb.getSerializationCode());
  }


  public void test_getSerializationCode_1DObject() {
    FieldBuilder fb = new ArrayFieldBuilder("example", "ArrayList<Example>");
    StringBuilder ans = new StringBuilder();
    ans.append("jsobj = new JSONObject();\n\t\t").append("arr = new JSONArray();\n\t\t")
        .append("for(int i = 0; i < " + fb.getFieldName() + ".size(); i++){\n\t\t\t")
        .append("arr.put(this." + fb.getFieldName() + ".get(i).toJSON(idMap));\n\t\t").append("}\n\t\t")
        .append("jsobj.put(\"" + fb.getFieldName() + "\", arr);\n\t\t").append("values.put(jsobj);\n\n\t\t");

    assertEquals(ans.toString(), fb.getSerializationCode());
  }
}
