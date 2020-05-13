package edu.duke.ece651.classbuilder;

public class ArrayFieldBuilder extends FieldBuilder {
  String ARRAYLIST_SIGN = "ArrayList<";
  String COLLECTION_SIGN = "Collection<";

  private int dimension;
  
  public ArrayFieldBuilder(String fieldName, String fieldType) {
    super(fieldName, fieldType);
    dimension = getDimension();
  }

  private int getDimension() {
    int dimension = 0;
    int pos = fieldType.length() - 1;
    while (fieldType.charAt(pos) == '>') {
      ++dimension;
      --pos;
    }
    return dimension;
  }

  @Override
  public String getFieldMethods() {
    StringBuilder sourceCode = new StringBuilder();
    generateGetter(sourceCode);
    sourceCode.append('\n');
    generateSetter(sourceCode);
    sourceCode.append('\n');
    generateAdder(sourceCode);
    sourceCode.append('\n');
    generateNumer(sourceCode);
    return sourceCode.toString();
  }
  
  private void generateGetter(StringBuilder sourceCode) {
        sourceCode.append("\tpublic ").append(getInnerArrayType()).append(" get").append(toCaptalizedName())
            .append("(int index) {\n\t\treturn this.").append(fieldName).append(".get(index);\n\t}\n");
  }

  private void generateSetter(StringBuilder sourceCode) {
    sourceCode.append("\tpublic void set").append(toCaptalizedName())
        .append("(int index, " + getInnerArrayType() + " f) {\n\t\tthis.").append(fieldName)
        .append(".set(index, f);\n\t}\n");
  }

  private void generateAdder(StringBuilder sourceCode) {
    sourceCode.append("\tpublic void add").append(toCaptalizedName())
        .append("(" + getInnerArrayType() + " f) {\n\t\tthis.").append(fieldName).append(".add(f);\n\t}\n");
  }

  private void generateNumer(StringBuilder sourceCode) {
    sourceCode.append("\tpublic int num").append(toCaptalizedName()).append("() {\n\t\treturn this.")
        .append(fieldName).append(".size();\n\t}\n");
  }
  
  private String getInnerArrayType() {
    return getInnerArrayType(fieldType);
  }

  private String getInnerArrayType(String collectionType) {
    if (collectionType.contains(ARRAYLIST_SIGN)) {
      return getInnerArrayType(ARRAYLIST_SIGN, collectionType);
    } else if (collectionType.contains(COLLECTION_SIGN)) {
      return getInnerArrayType(COLLECTION_SIGN, collectionType);
    }
    throw new IllegalArgumentException("argument is not a collection type");
  }

  private String getInnerArrayType(String arraySign, String collectionType) {
    String type = collectionType.substring(arraySign.length(), collectionType.length() - 1);
    if (isPrimitiveWrapper(type)) {
      return changeToPrimitive(type);
    }
    return type;
  }

  private String getInnerMostType() {
    int start = 1 + fieldType.lastIndexOf('<');
    String type = fieldType.substring(start, fieldType.length() - dimension);
    if (isPrimitiveWrapper(type)) {
      return changeToPrimitive(type);
    }
    return type;
  }

  /*
   * If fieldType is array, check if array inner type is primitive or String
   */
  private boolean isPrimitiveOrStringInnerMostType() {
    String innerMostType = getInnerMostType();
    return isPrimitive(innerMostType) || innerMostType.equals("String");
  }
  
  private boolean isPrimitiveWrapper(String type) {
    String[] primitiveWrapper = { "Boolean", "Byte", "Character", "Integer", "Long", "Float", "Double", "Short" };
    for (int i = 0; i < primitiveWrapper.length; i++) {
      if (type.equals(primitiveWrapper[i])) {
        return true;
      }
    }
    return false;
  }

  private String changeToPrimitive(String type) {
    if (type.equals("Integer")) {
      return "int";
    } else if (type.equals("Character")) {
      return "char";
    } else {
      StringBuilder typeBuilder = new StringBuilder(type);
      typeBuilder.setCharAt(0, (char) (typeBuilder.charAt(0) + 'a' - 'A'));
      return typeBuilder.toString();
    }
  }

  @Override
  public String getSerializationCode() {
    StringBuilder sourceCode = new StringBuilder();
    if (isPrimitiveOrStringInnerMostType()) {
      // primitive array
      generateSeriaizationPrimitiveArrayField(sourceCode);
    } else { // object array
      generateSerializationObjectArrayField(sourceCode);
    }
    return sourceCode.toString();
  }

  private void generateSeriaizationPrimitiveArrayField(StringBuilder sourceCode) {
    sourceCode.append("jsobj = new JSONObject();\n\t\t").append("arr = new JSONArray(this." + fieldName + ");\n\t\t")
        .append("jsobj.put(\"" + fieldName + "\", arr);\n\t\t").append("values.put(jsobj);\n\n\t\t");
  }

  private void generateSerializationObjectArrayField(StringBuilder sourceCode) {
    sourceCode.append("jsobj = new JSONObject();\n\t\t").append("arr = new JSONArray();\n\t\t");
    generateSerializationObjectArrayField(sourceCode, 1, fieldType, "");
    sourceCode.append("jsobj.put(\"" + fieldName + "\", arr);\n\t\t").append("values.put(jsobj);\n\n\t\t");
        // .append("for(int i = 0; i < " + fieldName + ".size(); i++){\n\t\t\t")
        // .append("arr.put(this." + fieldName + ".get(i).toJSON(idMap));\n\t\t").append("}\n\t\t")
    //.append("jsobj.put(\"" + fieldName + "\", arr);\n\t\t").append("values.put(jsobj);\n\n\t\t");
  }

  private void generateSerializationObjectArrayField(StringBuilder sourceCode, int dim, String collectionType,
      String tabOffset) {
    sourceCode.append("for(").append(getInnerArrayType(collectionType)).append(" target" + dim).append(" : ")
        .append(dim == 1 ? fieldName : "target" + (dim - 1)).append(") {\n\t\t\t" + tabOffset);
    if (dim == dimension) {
      generateSerializationObjectArrayFieldInnerMostForLoop(sourceCode, dim, tabOffset);
    } else {
      generateSerializatioinObjectArrayFieldOtherForLoop(sourceCode, dim, collectionType, tabOffset);
    }
  }

  private void generateSerializationObjectArrayFieldInnerMostForLoop(StringBuilder sourceCode, int dim,
      String tabOffset) {
    sourceCode.append("arr" + (dim - 1 == 0 ? "" : dim - 1))
        .append(".put(target" + dim + ".toJSON(idMap));\n\t\t" + tabOffset).append("}\n\t\t" + tabOffset);
  }

  private void generateSerializatioinObjectArrayFieldOtherForLoop(StringBuilder sourceCode, int dim,
      String collectionType, String tabOffset) {
    sourceCode.append("JSONArray arr" + dim + " = new JSONArray();\n\t\t\t" + tabOffset);
    generateSerializationObjectArrayField(sourceCode, dim + 1, getInnerArrayType(collectionType), tabOffset + '\t');
    sourceCode.append("arr" + (dim - 1 == 0 ? "" : dim - 1) + ".put(arr" + dim + ");\n\t\t" + tabOffset)
        .append("}\n\t\t" + tabOffset);
  }

  @Override
  public String getDeserializationCode() {
    StringBuilder sourceCode = new StringBuilder();
    sourceCode.append("arr1 = (JSONArray) fieldValuePairs.get(\"").append(fieldName).append("\");\n\t\t");
    generateDeserializationCode(sourceCode, 1, getInnerArrayType(), "");
    sourceCode.append("\n\t\t");
    return sourceCode.toString();
  }

  private void generateDeserializationCode(StringBuilder sourceCode, int dim, String collectionType, String tabOffset) {
    sourceCode.append(
        "for(int i" + dim + " = 0; i" + dim + " < arr" + dim + ".length(); i" + dim + "++) {\n\t\t\t" + tabOffset);
    if (dim == dimension) {
      generateDeserializationInnerMostForLoop(sourceCode, dim, tabOffset);
    } else {
      generateDeserializationOtherForLoop(sourceCode, dim, collectionType, tabOffset);
    }
  }

  private void generateDeserializationInnerMostForLoop(StringBuilder sourceCode, int dim, String tabOffset) {
    if (isPrimitiveOrStringInnerMostType()) {
      if(dim == 1){
      sourceCode.append("ans.add").append(toCaptalizedName()).append("((").append(getInnerArrayType())
          .append(") arr1.get(i" + dim + "));\n\t\t" + tabOffset + "}\n\t\t" + tabOffset);
    } else {
        sourceCode.append("target" + (dim - 1) + ".add((" + getInnerMostType() + ") arr" + dim + ".get(i" + dim + "));\n\t\t" + tabOffset).append("}\n\t\t" + tabOffset);
      }
      
    } else {
      sourceCode.append("obj = arr" + dim + ".getJSONObject(i" + dim +");\n\t\t\t" + tabOffset)
          .append(dim == 1 ? ("ans.add" + toCaptalizedName()) : "target" + (dim - 1) + ".add").append("(read")
          .append(getInnerMostType()).append("(obj, idMap));\n\t\t" + tabOffset + "}\n\t\t" + tabOffset);
    }
  }

  private void generateDeserializationOtherForLoop(StringBuilder sourceCode, int dim, String collectionType, String tabOffset) {
      sourceCode.append(collectionType).append(" target" + dim + " = new ArrayList<>();\n\t\t\t" + tabOffset)
          .append(
                  "JSONArray arr" + (dim + 1) + " = arr" + dim + ".getJSONArray(i" + dim + ");\n\t\t\t" + tabOffset);
      generateDeserializationCode(sourceCode, dim + 1, getInnerArrayType(collectionType), tabOffset + '\t');
      sourceCode.append(
          dim == 1 ? ("ans.add" + toCaptalizedName() + "(target1") : ("target" + (dim - 1) + ".add(target" + dim))
          .append(");\n\t\t" + tabOffset).append("}\n\t\t" + tabOffset);
  }
}
