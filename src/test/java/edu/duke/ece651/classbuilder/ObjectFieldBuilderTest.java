package edu.duke.ece651.classbuilder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ObjectFieldBuilderTest {
  @Test
  public void test_getSerializationCode() {
    FieldBuilder fb = new ObjectFieldBuilder("example", "Example");
    StringBuilder sourceCode = new StringBuilder();
    sourceCode.append("jsobj = new JSONObject();\n\t\t")
        .append("jsobj.put(\"" + fb.getFieldName() + "\", this." + fb.getFieldName() + ".toJSON(idMap));\n\t\t")
        .append("values.put(jsobj);\n\n\t\t");
    assertEquals(sourceCode.toString(), fb.getSerializationCode());
  }
}
