package edu.duke.ece651.classbuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClassBuilderTest {
  @Test
  public void test_getClassNames() {
    String str1 = "{ \'classes\' :[{\'name\' : \'Course\', \'fields\' : [ {\'name\' : \'instructor\', \'type\' : \'Faculty\'},{\'name\' : \'numStudents\', \'type\' : \'int\' }]}, {\'name\' : \'Office\', \'fields\' : [ {\'name\' : \'occupant\', \'type': \'Faculty\'},{\'name\' : \'number\', \'type\': \'int\'}, {\'name\' : \'building\' , \'type\': \'String\'}]}, {\'name\' : \'Faculty\', \'fields\' : [ {\'name\' : \'name\', \'type\' : \'String\' }]}, {\'name\' : \'Grade\', \'fields\' : [ {\'name\' : \'course\', \'type\' : \'Course\'}, {\'name\' : \'student\', \'type\' : \'String\'}, {\'name\' : \'grade\', \'type\': \'double\'}]}]}";
    String[] trueNames1 = { "Course", "Office", "Faculty", "Grade" };
    test_getClassNames(str1, trueNames1);

    String str2 = "{ \'classes\' : [{\'name\' : \'Test\', \'fields\' : [{\'name\' : \'x\', \'type\' : \'int\'}]}], \'package\' : \'edu.duke.ece651.hwk1.data\'}";
    String[] trueNames2 = { "Test" };
    test_getClassNames(str2, trueNames2);
  }

  private void test_getClassNames(String str, String[] trueNames) {
    ClassBuilder cb = new ClassBuilder(str);
    Collection<String> cnames = cb.getClassNames();
    assertEquals(cnames.size(), trueNames.length);
    for (int i = 0; i < trueNames.length; i++) {
      assertTrue(cnames.contains(trueNames[i]));
    }
  }

  @Test
  public void test_getSourceCode() throws IOException {
    String[] validFiles = { "1DArray_object.json", "1DArray_primitive.json", "multiArray_object.json",
        "multiArray_primitive.json", "nofields.json", "nonarray.json", "nameBeginWith_.json", "package.json" };
    for (int i = 0; i < validFiles.length; i++) {
        test_getSourceCode_valid(validFiles[i], i);
    }

    assertThrows(IllegalArgumentException.class, () -> {
      test_getSourceCode_Invalid("invalid_class_name.json");
    });
  }

  private void test_getSourceCode_valid(String fileName, int fileID) throws IOException {
    InputStream is = getClass().getResourceAsStream("/input/valid/" + fileName);
    ClassBuilder cb = new ClassBuilder(is);
    FileWriter srcCode = new FileWriter("src/test/resources/output/test" + fileID + ".java");
    PrintWriter pw = new PrintWriter(srcCode);
    pw.print(cb.getSourceCode("Test"));
    pw.close();
  }

  private void test_getSourceCode_Invalid(String fileName) throws IOException {
    InputStream is = getClass().getResourceAsStream("/input/invalid/" + fileName);
    ClassBuilder cb = new ClassBuilder(is);
    FileWriter srcCode = new FileWriter("src/test/resources/output/invalid.java");
    PrintWriter pw = new PrintWriter(srcCode);
    try {
      pw.print(cb.getSourceCode("Test"));
    } catch (IllegalArgumentException ex) {
      throw ex;
    } finally {
      pw.close();
    }
  }

  @Test
  public void test_createAllClasses() throws IOException {
    test_createAllClasses("multi_classes.json", "all_classes");
    test_createAllClasses("multi_classes_package.json", "all_classes_with_package");
  }

  private void test_createAllClasses(String inputFile, String basePath) throws IOException {
    InputStream is = getClass().getResourceAsStream("/input/valid/" + inputFile);
    ClassBuilder cb = new ClassBuilder(is);
    cb.createAllClasses("src/test/resources/output/" + basePath);
  }

  @Test
  public void test_toJSON() throws IOException{
    test_createAllClasses("person_dog.json", "toJSON");
  }
}
