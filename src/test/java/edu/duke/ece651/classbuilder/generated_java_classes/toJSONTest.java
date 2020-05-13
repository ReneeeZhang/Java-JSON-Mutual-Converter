package edu.duke.ece651.classbuilder.generated_java_classes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class toJSONTest {
  @Test
  public void test_Person_Dog() {
    Person p = new Person();
    Dog d0 = new Dog();
    //pets
    Dog d1 = new Dog();
    Dog d2 = new Dog();
    Dog d3 = new Dog();
    Dog d4 = new Dog();
    Dog d5 = new Dog();
    Dog d6 = new Dog();
    Dog d7 = new Dog();
    Dog d8 = new Dog();
        
    //set pet
    d0.setName("Fido");
    d0.setOwner(p);

    //set pets
    d1.setName("Fido1");
    d1.setOwner(p);
    d2.setName("Fido2");
    d2.setOwner(p);
    d3.setName("Fido3");
    d3.setOwner(p);

    //set petGroups
    d4.setName("Fido4");
    d4.setOwner(p);
    d5.setName("Fido5");
    d5.setOwner(p);
    d6.setName("Fido6");
    d6.setOwner(p);
    d7.setName("Fido7");
    d7.setOwner(p);
    d8.setName("Fido8");
    d8.setOwner(p);
    
    Collection<Dog> group1 = new ArrayList<>();
    Collection<Dog> group2 = new ArrayList<>();

    group1.add(d4);
    group1.add(d5);
    group1.add(d6);

    group2.add(d7);
    group2.add(d8);
    group2.add(d0);

    p.addPetGroups(group1);
    p.addPetGroups(group2);
    
    //set person
    p.setName("Joe");
    p.setPet(d0);
    p.setAge(42);
    
    p.addPets(d1);
    p.addPets(d2);
    p.addPets(d3);

    p.addTimes(1);
    p.addTimes(2);
    p.addTimes(3);

    Collection<Integer> timeGroup1 = new ArrayList<>();
    Collection<Integer> timeGroup2 = new ArrayList<>();

    timeGroup1.add(4);
    timeGroup1.add(5);
    timeGroup2.add(6);
    timeGroup2.add(7);
    timeGroup2.add(8);
    
    p.addTimeGroups(timeGroup1);
    p.addTimeGroups(timeGroup2);
    
    JSONObject obj = p.toJSON();
    assertEquals(0, obj.getInt("id"));
    assertEquals("Person", obj.getString("type"));
    //JSONArray arr = obj.getJSONArray("values");
    //System.out.println(p.toJSON().get("values"));
    System.out.println(p.toJSON());
  }

}
