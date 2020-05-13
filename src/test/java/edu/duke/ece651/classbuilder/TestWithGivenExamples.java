package edu.duke.ece651.classbuilder;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

public class TestWithGivenExamples {
  @Test
  public void generateExampleJava() throws IOException{
    generateExampleJava("arr.json", "arr");
    generateExampleJava("crazyArrays.json", "crazyArrays");
    generateExampleJava("empty.json", "empty");
    generateExampleJava("mdarr.json", "mdarr");
    generateExampleJava("nameRef.json", "nameRef");
    generateExampleJava("prims.json", "prims");
    generateExampleJava("simple.json", "simple");
    generateExampleJava("simplearray.json", "simplearray");
    generateExampleJava("zoo.json", "zoo");    
  }

  private void generateExampleJava(String inputFile, String basePath) throws IOException {
    InputStream is = getClass().getResourceAsStream("/input/test_infrastructure/" + inputFile);
    ClassBuilder cb = new ClassBuilder(is);
    cb.createAllClasses("src/test/resources/output/test_infrastructure/" + basePath);
  }
}
