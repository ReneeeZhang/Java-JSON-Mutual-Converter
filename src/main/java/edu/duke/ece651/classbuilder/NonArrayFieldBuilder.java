package edu.duke.ece651.classbuilder;

public abstract class NonArrayFieldBuilder extends FieldBuilder {

  public NonArrayFieldBuilder(String fieldName, String fieldType) {
    super(fieldName, fieldType);
  }
  
  @Override
  public String getFieldMethods() {
    StringBuilder sourceCode = new StringBuilder();
    generateGetter(sourceCode);
    sourceCode.append('\n');
    generateSetter(sourceCode);
    return sourceCode.toString();
  }

  private void generateGetter(StringBuilder sourceCode) {
    sourceCode.append("\tpublic ").append(fieldType + " get").append(toCaptalizedName())
        .append("() {\n\t\treturn this.").append(fieldName + ";\n\t}\n");
  }

  private void generateSetter(StringBuilder sourceCode) {
    sourceCode.append("\tpublic void set").append(toCaptalizedName()).append("(" + fieldType + " f){\n\t\tthis.")
        .append(fieldName).append(" = ").append("f;\n\t}\n");
  }

  public abstract String getSerializationCode();

  public abstract String getDeserializationCode();
}
