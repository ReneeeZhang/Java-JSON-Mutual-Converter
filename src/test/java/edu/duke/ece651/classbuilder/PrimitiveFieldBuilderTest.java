package edu.duke.ece651.classbuilder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PrimitiveFieldBuilderTest {
  @Test
  public void test_getSerializationCode() {
    FieldBuilder fb = new PrimitiveFieldBuilder("example", "int");
    StringBuilder ans = new StringBuilder();
    ans.append("jsobj = new JSONObject();\n\t\t")
        .append("jsobj.put(\"" + fb.getFieldName() + "\", this." + fb.getFieldName() + ");\n\t\t")
        .append("values.put(jsobj);\n\n\t\t");
    assertEquals(ans.toString(), fb.getSerializationCode());
  }

}
