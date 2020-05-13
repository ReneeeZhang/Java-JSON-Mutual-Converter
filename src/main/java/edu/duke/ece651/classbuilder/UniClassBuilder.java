package edu.duke.ece651.classbuilder;

import org.json.JSONArray;
import org.json.JSONObject;

public class UniClassBuilder {
  private static final String CLASS_NAME_TAG = "name";
  private static final String FIELDS_TAG = "fields";

  private String uniClassName;
  private FieldBuilder[] fields; // Includeing 1. fieldType; 2. fieldName

  public UniClassBuilder(JSONObject oneClass) {
    uniClassName = oneClass.getString(CLASS_NAME_TAG);
    JSONArray fieldsArr = oneClass.optJSONArray(FIELDS_TAG);
    if (fieldsArr != null) {
      fields = new FieldBuilder[fieldsArr.length()];
      for (int i = 0; i < fieldsArr.length(); i++) {
        FieldClassifier fc = new FieldClassifier(fieldsArr.getJSONObject(i));
        fields[i] = fc.classify();
      }
    } else {
      fields = null;
    }
  }

  public String getUniClassName() {
    return uniClassName;
  }

  public String getSourceCode(String pkgCode) {
    StringBuilder sourceCode = new StringBuilder(pkgCode);
    sourceCode.append("import java.util.*;\nimport org.json.*;\n\n");
    generateClassTitle(sourceCode);
    if (fields != null) {
      generateFields(sourceCode);
      sourceCode.append('\n');

      generateCtor(sourceCode);
      sourceCode.append('\n');

      generateFieldMethods(sourceCode);
      sourceCode.append('\n');
    }
    
    generateToJSON(sourceCode);
    sourceCode.append('\n');
    
    generateToJSONHelper(sourceCode);
    sourceCode.append('}');
    return sourceCode.toString();
  }

  /***********************************
   * Methods to help build getSourceCode
   ***********************************/
  private void generateClassTitle(StringBuilder sourceCode) {
    sourceCode.append("public class ").append(uniClassName).append(" {\n");
  }

  private void generateFields(StringBuilder sourceCode) {
    for (int i = 0; i < fields.length; i++) {
      sourceCode.append("\tprivate ").append(fields[i].getFieldType() + ' ').append(fields[i].getFieldName() + ";\n");
    }
  }

  private void generateCtor(StringBuilder sourceCode) {
    sourceCode.append("\tpublic ").append(uniClassName).append("() {\n\t\t");
    for (int i = 0; i < fields.length; i++) {
      if (fields[i].isArray()) {
        sourceCode.append(fields[i].getFieldName()).append(" = new ArrayList<>();\n\t\t");
      }
    }
    sourceCode.delete(sourceCode.length() - 3, sourceCode.length() - 1);
    sourceCode.append("\n\t}\n");
  }

  private void generateFieldMethods(StringBuilder sourceCode) {
    for (int i = 0; i < fields.length; i++) {
      sourceCode.append(fields[i].getFieldMethods());
      sourceCode.append('\n');
    }
  }

  private void generateToJSON(StringBuilder sourceCode) {
    sourceCode.append(
        "\tpublic JSONObject toJSON() throws JSONException {\n\t\tMap<Object, Integer> idMap = new HashMap<>();\n\t\treturn toJSON(idMap);")
        .append("\n\t}\n");
  }

  private void generateToJSONHelper(StringBuilder sourceCode) {
    sourceCode.append("\tpublic JSONObject toJSON(Map<Object, Integer> idMap) throws JSONException {\n\t\t")
        .append("JSONObject ans = new JSONObject();\n\t\t")
        .append(
            "if(idMap.containsKey(this)){\n\t\t\tans.put(\"ref\", idMap.get(this));\n\t\t\treturn ans;\n\t\t}\n\n\t\t")
        .append("ans.put(\"id\", idMap.size());\n\t\t").append("ans.put(\"type\", \"" + uniClassName + "\");\n\t\t")
        .append("idMap.put(this, idMap.size());\n\n\t\t").append("JSONArray values = new JSONArray();\n\t\t")
        .append("JSONObject jsobj;\n\t\t").append("JSONArray arr;\n\n\t\t");

    generateToJSONFields(sourceCode);

    sourceCode.append("ans.put(\"values\", values);\n\t\t").append("return ans;\n\t}\n");
  }

  private void generateToJSONFields(StringBuilder sourceCode) {
    if(fields == null){
      return;
    }
    for (int i = 0; i < fields.length; i++) {
      sourceCode.append(fields[i].getSerializationCode());
    }
  }

  /*******************************
   * Deserializer
   *******************************/  
  public String getDeserializerCode() {
    StringBuilder sourceCode = new StringBuilder();
    generateDeserializerCode(sourceCode);
    sourceCode.append("\n\t");
    generateDeserializerHelperCode(sourceCode);
    return sourceCode.toString();
  }

  private void generateDeserializerCode(StringBuilder sourceCode) {
    sourceCode.append("\tpublic static ").append(uniClassName).append(" read").append(uniClassName)
        .append("(JSONObject js) throws JSONException {\n\t\t")
      .append("Map<Integer, Object> idMap = new HashMap<>();\n\t\t").append("return read").append(uniClassName).append("(js, idMap);\n\t}\n");
  }
  
  private void generateDeserializerHelperCode(StringBuilder sourceCode) {
    generateDeserializerHelperTitle(sourceCode);
    generateDeserializerHelperBody(sourceCode);
  }

  private void generateDeserializerHelperTitle(StringBuilder sourceCode) {
    // Deserializer helper title
    sourceCode.append("private static ").append(uniClassName).append(" read").append(uniClassName)
        .append("(JSONObject js, Map<Integer, Object> idMap) throws JSONException {\n\t\t");
  }
  
  private void generateDeserializerHelperBody(StringBuilder sourceCode) {
    // Deserializer helper body - handle ref
    sourceCode.append("try {\n\t\t\t").append("int ref = js.getInt(\"ref\");\n\t\t\t").append("return (")
        .append(uniClassName).append(") idMap.get(ref);\n\t\t} ").append("catch(JSONException ex) {}\n\n\t\t");
    // Deserializer helper body - handle the rest
    sourceCode.append(uniClassName).append(" ans = new ").append(uniClassName).append("();\n\t\t")
        .append("int id = js.getInt(\"id\");\n\t\t").append("idMap.put(id, ans);\n\t\t")
        .append("JSONArray valuesArr = js.getJSONArray(\"values\");\n\t\t")
        .append("Map<String, Object> fieldValuePairs = new HashMap<>();\n\t\t")
        .append("for(int i = 0; i < valuesArr.length(); i++) {\n\t\t\t")
        .append("JSONObject kvpair = valuesArr.getJSONObject(i);\n\t\t\t")
        .append("for(String fieldName : kvpair.keySet()) {\n\t\t\t\t")
        .append("fieldValuePairs.put(fieldName, kvpair.get(fieldName));\n\t\t\t}\n\t\t}\n\n\t\t");

    if (fields == null) {
      return;
    }
    sourceCode.append("JSONObject obj;\n\t\t").append("JSONArray arr1;\n\n\t\t");
    for (int i = 0; i < fields.length; i++) {
      sourceCode.append(fields[i].getDeserializationCode());
    }
    sourceCode.append("return ans;\n\t}\n");
  }  
}
