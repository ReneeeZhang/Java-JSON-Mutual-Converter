package edu.duke.ece651.classbuilder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClassBuilder {
  private static final String CLASS_TAG = "classes";
  private static final String PACKAGE_TAG = "package";

  private String packageField;
  private UniClassBuilder[] allClasses;

  public ClassBuilder(String strJSONObj) throws JSONException {
    constructFromString(strJSONObj);
  }

  public ClassBuilder(InputStream is) throws IOException, JSONException {
    ByteArrayOutputStream result = new ByteArrayOutputStream();
    byte[] buffer = new byte[1024];
    int len = is.read(buffer);
    while (len != -1) {
      result.write(buffer, 0, len);
      len = is.read(buffer);
    }
    constructFromString(result.toString());
  }

  public Collection<String> getClassNames() {
    Collection<String> allClassNames = new ArrayList<>(allClasses.length);
    for (int i = 0; i < allClasses.length; i++) {
      allClassNames.add(allClasses[i].getUniClassName());
    }
    return allClassNames;
  }

  public String getSourceCode(String className) throws IllegalArgumentException {
    UniClassBuilder ucb = searchTarget(className);
    return ucb.getSourceCode(getPkgCode());
  }

  public void createAllClasses(String basePath) throws IOException {
    String packagePath = "";
    if (packageField != null) {
      packagePath = packageField.replace('.', '/');
    }
    File dir = new File(basePath, packagePath);
    if (!dir.exists()) {
      dir.mkdirs();
    }

    for (int i = 0; i < allClasses.length; i++) {
      String className = allClasses[i].getUniClassName();
      String source = getSourceCode(className);
      FileWriter fw = new FileWriter(dir.getPath() + '/' + className + ".java");
      PrintWriter pw = new PrintWriter(fw);
      pw.print(source);
      pw.close();
    }

    createDeserializer(dir);
  }
  

  /*************************************
  * Deserializer  
  *************************************/
  private void createDeserializer(File dir) throws IOException {
    FileWriter fw = new FileWriter(dir.getPath() + "/Deserializer.java");
    PrintWriter pw = new PrintWriter(fw);
    String deserializerSourceCode = getDeserializerSourceCode();
    pw.print(deserializerSourceCode);
    pw.close();
  }

  private String getDeserializerSourceCode() {
    StringBuilder sourceCode = new StringBuilder();
    // package info at the top
    sourceCode.append(getPkgCode());
    sourceCode.append("import java.util.*;\nimport org.json.*;\n\n");
    generateDeserializerClassTitle(sourceCode);
    for (int i = 0; i < allClasses.length; i++) {
      sourceCode.append(allClasses[i].getDeserializerCode());
      sourceCode.append("\n");
    }  
    sourceCode.append('}'); // closing bracket for Deserializer class
    return sourceCode.toString();
  }
  
  private void generateDeserializerClassTitle(StringBuilder sourceCode) {
    sourceCode.append("public class Deserializer {\n");
  }
  
  
  // Helper method for constructors
  private void constructFromString(String strJSONObj) throws JSONException {
    JSONObject obj = new JSONObject(strJSONObj);
    JSONArray classes = obj.getJSONArray(CLASS_TAG);
    allClasses = new UniClassBuilder[classes.length()];
    packageField = obj.optString(PACKAGE_TAG);
    if (packageField.equals("")) {
      packageField = null;
    }
    for (int i = 0; i < classes.length(); i++) {
      JSONObject oneClass = classes.getJSONObject(i);
      allClasses[i] = new UniClassBuilder(oneClass);
    }
  }
  
    private UniClassBuilder searchTarget(String className) throws IllegalArgumentException {
    for (int i = 0; i < allClasses.length; i++) {
      if (allClasses[i].getUniClassName().equals(className)) {
        return allClasses[i];
      }
    }
    throw new IllegalArgumentException("Target class name does not exist!");
  }
  
  private String getPkgCode() {
    if (packageField != null) {
      return PACKAGE_TAG + ' ' + packageField + ";\n\n";
    }
    return "";
  }  
}
