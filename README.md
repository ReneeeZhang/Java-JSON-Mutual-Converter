# Java - JSON Mutual Converter
This repository is an assignment of [ECE 651 (Software Engineering)](https://adhilton.pratt.duke.edu/ece-651) Spring 2020, Duke University.
Instructor: [Prof. Andrew D. Hilton](https://ece.duke.edu/faculty/andrew-hilton).

## Overview
The goal is to make a program which reads in a JSON file, and produces a set of Java classes (the source code) based on the description in the JSON. The resulting classes will have fields/methods as described in the JSON file, as well as methods to serialize and deserialize the resulting objects into a JSON format.

This can be regarded as a way to take a specification of the data layout, and create a class for it, as well as to save them and send them over the network and etc, and then load them back.

## What I Achieved
- Combined top-down and bottom-up design strategies, starting from small pieces but guiding by higher level modules.
- Made good use of OO design principles, writing clean code by dynamic dispatch and abstract factory pattern.
- Be able to convert between Java objects and JSON by (de)serialization, recursively handling different field types.

## To Begin With
- Project type: Application
- Build script DSL: Groovy
- Test framework: JUnit Jupiter
- The JSON format in this project is based on this [JSON library](https://stleary.github.io/JSON-java/).

## Requirements

### ClassBuilder
There shall be a major class named ClassBuilder, which shall have:
1. A constructor that takes a String. This String shall be interpreted as the literal text of the JSON description.
   - This constructor should throw an appropriate exception if the JSON is not valid, or does not conform to the input specification requirements.
2. A constructor that takes an InputStream. This InputStream shall be read from to obtain the text of the JSON description.
   - This constructor should throw an appropriate exception if an IO error occurs reading from the InputStream.
   - This constructor should throw an appropriate exception if the JSON is not valid, or does not conform to the input specification requirements.
3. A public method: <br/>*Collection<String> getClassNames()*,<br/>which returns the names of all classes that were described by the JSON description (which was read in a constructor).
4. A public method: <br/>*String getSourceCode(String className)*,<br/>which returns the text of the source code you have generated for the specified class name. This method should return valid Java source code (could be saved to a file, and compiled with javac, or an appropriately configured gradle project) whenever className is a member of the return value of getClassNames().
5. A public method: <br/>*void createAllClasses(String basePath)*,<br/>which should create one source file per class. These should all be made relative to the specified base path. If the classes are within a package, they will need to be in subdirectories of that path. If they are not in a package, then they will be made directly in that path.

To see ClassBuilder.java, click [here](src/main/java/edu/duke/ece651/classbuilder/ClassBuilder.java).

### JSON File Format
1. There shall be one top-level object. That object will have:
   - A required field named "classes" whose value shall be an an array of class descriptions. See 2 for information about class descriptions.
   - An optional field named "package". If this field is present, its value shall be a string, which specifies the package that the resulting classes shall reside in. Accordingly, it must be a valid Java package name.
2. A class description shall have the following fields:
   - A required field named "name" which shall be a String specifying the class name. This must be a valid Java class name.
   - An optional field named "fields". If this is present, its value shall be an array of one or more field descriptors. See 3 for information about field descriptors. If this field is not present, the class has no fields.
3. A field descriptor shall have the following fields:
   - A required field called "name" which shall be a string specifying the name of the field. Accordingly, it must be a valid Java identifier.
   - A required field called "type" which shall be a string specifying the type of the field. This must be one of the following:
     - "boolean", "byte", "char","short","int", "long", "float" "double" to specify the corresponding Java primitive type
     - "String" to specify a string
     - Any of the class names specified in the input file to specify the corresponding class.
     - A JSON object with one field named "e" whose value must be any of the types specified in this list above. Such a specification makes a sequence (e.g., array) of the type specified.<br/>For example "type" : {"e": "int"} specifies an array of ints. Note that the definition of this clause is recursive, so it must support arbitrary nesting.<br/>"type" : {"e": {"e": "int"}}<br/> "type" : {"e": {"e": {"e": "int"}}} etc. are all legal, and specify an array of arrays of ints, and an array of arrays of arrays of ints respectively.

### Output Classes
1. Every generated field shall be private.
2. Fields of non-array type shall have public get/set methods. These shall be named with get (or set) followed by the capitalized field name.<br/>For example, if the field is *int height*, the methods will be
   - int getHeight()
   - void setHeight(int h)<br/>The name of of parametes for the generated methods can be anything as long as the generated code works.
3. Array fields shall have four methods, *numX, addX, getX, and setX*, where X is the capitalized field name. The numX method shall return the number of elements in the array. The addX method shall add an element to the end of the array. The get/set methods shall take an index, and return/set that particular element of the array. For example, if the field is an String array called names, then the methods would be:
   - int numNames()
   - void addNames(String n)
   - String getNames(int index)
   - void setNames(int index, String n)<br/>
   Note: even though it is natural to singularize the field name (addName instead of addNames), do not try to do it. Just use the field name, but capitalize it.
4. Even though we refer to these fields as "arrays", they can be stored internally with a different representation (e.g., ArrayList)
5. Whenever a field is a multi-dimensional array, the add/get/set methods will return/take Collection<T> to avoid enforcing a specific representation. *set* method must ensure that it can handle any collection.<br/>
For example, if the field a 2D array of ints called data, then the methods would be:<br/>
   - void addData(Collection<Integer> d)
   - Collection<int> getData(int index)
   - void setData(int index, Collection<Integer> d)
6. A class with any array fields **must** have a **public** constructor. A class with no array fields may have a constructor if you wish. The constructor should initialize all array fields to a new empty array. Non-array fields do not need any explicit initialization (this may just be left as the default value Java initializes their type to).
7. Each class shall have a public method *JSONObject toJSON() throws JSONException* which shall return a JSONObject serializing the state of this object, as specified [here](https://gitlab.oit.duke.edu/yz545/yz545-json-651/-/blob/master/README.md#serialization)
8. In addition to the classes specified by the input file, your program should create a class called Deserializer. This class shall have one public static method per input class, which shall be named:<br/><br/>
*public static X readX(JSONObject js) throws JSONException*<br/><br/>
here X is the name of the class (capitalized in the method name). For example, if you have input classes named Person, Course, and Grade, your Deserializer class would have
   - public static Person readPerson(JSONObject js) throws JSONException
   - public static Course readCourse(JSONObject js) throws JSONException
   - public static Grade readGrade(JSONObject js) throws JSONException<br/><br/>

   as its methods.
9. You are NOT required to generate nicely formatted code. You are certainly welcome to indent things nicely, as if you had written the code in Emacs. However, if you don't it is ok. We do suggest that you at least include reasonable line breaks to make reading your output (and thus, debugging it) easier.

### Serialization
1. The serialized output shall have one top-level JSON object, which is the serialized representation of the object on which toJSON() was called.
2. The serialized representation of a Java object shall be a JSON object with three fields:
   - "id": a unique id
   - "type": the name of the class (as in the input file)
   - "values": an array of field:value pairs. E.g., an object should look like this:<br/>
   \{"id" : uniqueid,<br/> "type" : classname,<br/> "values" : [{"f1" : v1}, {"f2" : v2}, ..., {"fn" : vn}]\}
3. The unique identifiers must be integers, and may be created by any scheme you desire, so long as they are unique within a particular serialization.
4. For fields which are of primitive of String type, the value (in the "values" field) shall be written literally.
5. For fields which are of object type, upon the first occurrence of that particular instance of the object, the value shall be written out by its serialized representation from Serialization.2
6. For subsequent references to a previously serialized object, the value shall be serialized as {"ref": uniqueid} using the uniqueid written when the object was first serialized in Serialization.5.
7. For fields which are arrays, the serialized representation shall be a JSON array containing the serialization of the elements in the Java array.

### Deserialization
1. Deserialization should produce one or more Java objects that reconstruct the object reference graph and values in those objects at the point where the original object was serialized.
2. The serialization/deserialization methods must be able to handle objects with cycles in their reference graph.
3. Serialization followed by deserialization must produce objects that are logically equivalent. Put another way, if we were to properly define .equals() on our classes \[which you do not need to do\], then<br/><br/>
*Deserializer.readThing(x.toJSON()).equals(x)*<br/><br/>
should be true, and should not throw any exceptions.
4. Deserialization followed by re-serialization should be idempotent. In particular, if your scheme for generating unique identifiers is deterministically repeatable, then<br/><br/>
*Deserializers.readThing(x.toJSON()).toJSON().equals(x.toJSON())*<br/><br/>
should always be true, and should not throw any exceptions.<br/>
If your scheme for generating unique identifiers produces different identifiers on subsequent executions, then the only differences between<br/><br/>
*Deserializers.readThing(x.toJSON()).toJSON()*<br/>
and<br/>
*x.toJSON()*<br/><br/>
should be the particular values of the unique identifiers used.

## My Approach to Build
#### UniClassBuilder
In order to build all the classes described in a JSON file, I break it down to build a single class.<br/>
Note that getting the source code of this class, generating [toJSON()](https://gitlab.oit.duke.edu/yz545/yz545-json-651/-/blob/master/README.md#serialization) and [Deserializer methods](https://gitlab.oit.duke.edu/yz545/yz545-json-651/-/blob/master/README.md#deserialization) are all completed at this level.

To see UniClassBuilder.java, click [here](src/main/java/edu/duke/ece651/classbuilder/UniClassBuilder.java).

#### FieldBuilder
In order to build a singe class, I need to build each field in this class at first. Here, FieldBuilder is an abstract class. Inheritance hierarchy goes as follows, where `A <-- B` indicates B inherits from A.
- FieldBuilder <-- ArrayFieldBuilder
- FieldBuilder <-- NonArrayFieldBuilder <-- PrimitiveFieldBuilder
- FieldBuilder <-- NonArrayFieldBuilder <-- ObjectFieldBuilder
