package edu.duke.ece651.classbuilder;

public class ObjectFieldBuilder extends NonArrayFieldBuilder {

  public ObjectFieldBuilder(String fieldName, String fieldType) {
    super(fieldName, fieldType);
  }

  @Override
  public String getDeserializationCode() {
    StringBuilder sourceCode = new StringBuilder();
    sourceCode.append("obj = (JSONObject) fieldValuePairs.get(\"").append(fieldName).append("\");\n\t\t").append("ans.set").append(toCaptalizedName()).append("(read").append(fieldType)
        .append("(obj, idMap));\n\n\t\t");
    return sourceCode.toString();
  }
  
  @Override
  public String getSerializationCode() {
    StringBuilder sourceCode = new StringBuilder();
    sourceCode.append("jsobj = new JSONObject();\n\t\t")
        .append("jsobj.put(\"" + fieldName + "\", this." + fieldName + ".toJSON(idMap));\n\t\t")
        .append("values.put(jsobj);\n\n\t\t");
    return sourceCode.toString();
  }
}
