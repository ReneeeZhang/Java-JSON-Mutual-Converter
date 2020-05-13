package edu.duke.ece651.classbuilder.generated_java_classes;

import java.util.*;
import org.json.*;

public class Deserializer {
	public static Person readPerson(JSONObject js) throws JSONException {
		Map<Integer, Object> idMap = new HashMap<>();
		return readPerson(js, idMap);
	}

	private static Person readPerson(JSONObject js, Map<Integer, Object> idMap) throws JSONException {
		try {
			int ref = js.getInt("ref");
			return (Person) idMap.get(ref);
		} catch(JSONException ex) {}

		Person ans = new Person();
		int id = js.getInt("id");
		idMap.put(id, ans);
		JSONArray valuesArr = js.getJSONArray("values");
		Map<String, Object> fieldValuePairs = new HashMap<>();
		for(int i = 0; i < valuesArr.length(); i++) {
			JSONObject kvpair = valuesArr.getJSONObject(i);
			for(String fieldName : kvpair.keySet()) {
				fieldValuePairs.put(fieldName, kvpair.get(fieldName));
			}
		}

		JSONObject obj;
		JSONArray arr1;

		ans.setName((String) fieldValuePairs.get("name"));

		obj = (JSONObject) fieldValuePairs.get("pet");
		ans.setPet(readDog(obj, idMap));

		ans.setAge((int) fieldValuePairs.get("age"));

		arr1 = (JSONArray) fieldValuePairs.get("pets");
		for(int i1 = 0; i1 < arr1.length(); i1++) {
			obj = arr1.getJSONObject(i1);
			ans.addPets(readDog(obj, idMap));
		}
		
		arr1 = (JSONArray) fieldValuePairs.get("petGroups");
		for(int i1 = 0; i1 < arr1.length(); i1++) {
			Collection<Dog> target1 = new ArrayList<>();
			JSONArray arr2 = arr1.getJSONArray(i1);
			for(int i2 = 0; i2 < arr2.length(); i2++) {
				obj = arr2.getJSONObject(i2);
				target1.add(readDog(obj, idMap));
			}
			ans.addPetGroups(target1);
		}
		
		arr1 = (JSONArray) fieldValuePairs.get("times");
		for(int i1 = 0; i1 < arr1.length(); i1++) {
			ans.addTimes((long) arr1.get(i1));
		}
		
		arr1 = (JSONArray) fieldValuePairs.get("timeGroups");
		for(int i1 = 0; i1 < arr1.length(); i1++) {
			Collection<Integer> target1 = new ArrayList<>();
			JSONArray arr2 = arr1.getJSONArray(i1);
			for(int i2 = 0; i2 < arr2.length(); i2++) {
				target1.add((int) arr2.get(i2));
			}
			ans.addTimeGroups(target1);
		}
		
		return ans;
	}

	public static Dog readDog(JSONObject js) throws JSONException {
		Map<Integer, Object> idMap = new HashMap<>();
		return readDog(js, idMap);
	}

	private static Dog readDog(JSONObject js, Map<Integer, Object> idMap) throws JSONException {
		try {
			int ref = js.getInt("ref");
			return (Dog) idMap.get(ref);
		} catch(JSONException ex) {}

		Dog ans = new Dog();
		int id = js.getInt("id");
		idMap.put(id, ans);
		JSONArray valuesArr = js.getJSONArray("values");
		Map<String, Object> fieldValuePairs = new HashMap<>();
		for(int i = 0; i < valuesArr.length(); i++) {
			JSONObject kvpair = valuesArr.getJSONObject(i);
			for(String fieldName : kvpair.keySet()) {
				fieldValuePairs.put(fieldName, kvpair.get(fieldName));
			}
		}

		JSONObject obj;
		JSONArray arr1;

		ans.setName((String) fieldValuePairs.get("name"));

		obj = (JSONObject) fieldValuePairs.get("owner");
		ans.setOwner(readPerson(obj, idMap));

		return ans;
	}

}
