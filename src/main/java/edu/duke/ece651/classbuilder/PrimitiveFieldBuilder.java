package edu.duke.ece651.classbuilder;

public class PrimitiveFieldBuilder extends NonArrayFieldBuilder {

  public PrimitiveFieldBuilder(String fieldName, String fieldType) {
    super(fieldName, fieldType);
  }

  @Override
  public String getDeserializationCode() {
    StringBuilder sourceCode = new StringBuilder();
    sourceCode.append("ans.set").append(toCaptalizedName()).append("((").append(fieldType).append(") fieldValuePairs.get(\"").append(fieldName)
        .append("\"));\n\n\t\t");
    return sourceCode.toString();
  }
  
  @Override
  public String getSerializationCode() {
    StringBuilder sourceCode = new StringBuilder();
    sourceCode.append("jsobj = new JSONObject();\n\t\t")
        .append("jsobj.put(\"" + fieldName + "\", this." + fieldName + ");\n\t\t").append("values.put(jsobj);\n\n\t\t");
    return sourceCode.toString();
  }
}
