package hwk1.testing.zoo;

import java.util.*;
import org.json.*;

public class Deserializer {
	public static Keeper readKeeper(JSONObject js) throws JSONException {
		Map<Integer, Object> idMap = new HashMap<>();
		return readKeeper(js, idMap);
	}

	private static Keeper readKeeper(JSONObject js, Map<Integer, Object> idMap) throws JSONException {
		try {
			int ref = js.getInt("ref");
			return (Keeper) idMap.get(ref);
		} catch(JSONException ex) {}

		Keeper ans = new Keeper();
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

		ans.setFirstName((String) fieldValuePairs.get("firstName"));

		ans.setLastName((String) fieldValuePairs.get("lastName"));

		ans.setSalary((double) fieldValuePairs.get("salary"));

		arr1 = (JSONArray) fieldValuePairs.get("myPens");
		for(int i1 = 0; i1 < arr1.length(); i1++) {
			obj = arr1.getJSONObject(i1);
			ans.addMyPens(readAnimalPen(obj, idMap));
		}
		
		return ans;
	}

	public static AnimalPen readAnimalPen(JSONObject js) throws JSONException {
		Map<Integer, Object> idMap = new HashMap<>();
		return readAnimalPen(js, idMap);
	}

	private static AnimalPen readAnimalPen(JSONObject js, Map<Integer, Object> idMap) throws JSONException {
		try {
			int ref = js.getInt("ref");
			return (AnimalPen) idMap.get(ref);
		} catch(JSONException ex) {}

		AnimalPen ans = new AnimalPen();
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

		ans.setXloc((float) fieldValuePairs.get("xloc"));

		ans.setYloc((float) fieldValuePairs.get("yloc"));

		ans.setWidth((float) fieldValuePairs.get("width"));

		ans.setLength((float) fieldValuePairs.get("length"));

		ans.setHeight((float) fieldValuePairs.get("height"));

		arr1 = (JSONArray) fieldValuePairs.get("animals");
		for(int i1 = 0; i1 < arr1.length(); i1++) {
			obj = arr1.getJSONObject(i1);
			ans.addAnimals(readAnimal(obj, idMap));
		}
		
		ans.setSignText((String) fieldValuePairs.get("signText"));

		return ans;
	}

	public static Animal readAnimal(JSONObject js) throws JSONException {
		Map<Integer, Object> idMap = new HashMap<>();
		return readAnimal(js, idMap);
	}

	private static Animal readAnimal(JSONObject js, Map<Integer, Object> idMap) throws JSONException {
		try {
			int ref = js.getInt("ref");
			return (Animal) idMap.get(ref);
		} catch(JSONException ex) {}

		Animal ans = new Animal();
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

		ans.setMyName((String) fieldValuePairs.get("myName"));

		ans.setSpecies((String) fieldValuePairs.get("species"));

		ans.setAge((int) fieldValuePairs.get("age"));

		arr1 = (JSONArray) fieldValuePairs.get("foodsByPreference");
		for(int i1 = 0; i1 < arr1.length(); i1++) {
			Collection<Food> target1 = new ArrayList<>();
			JSONArray arr2 = arr1.getJSONArray(i1);
			for(int i2 = 0; i2 < arr2.length(); i2++) {
				obj = arr2.getJSONObject(i2);
				target1.add(readFood(obj, idMap));
			}
			ans.addFoodsByPreference(target1);
		}
		
		return ans;
	}

	public static Food readFood(JSONObject js) throws JSONException {
		Map<Integer, Object> idMap = new HashMap<>();
		return readFood(js, idMap);
	}

	private static Food readFood(JSONObject js, Map<Integer, Object> idMap) throws JSONException {
		try {
			int ref = js.getInt("ref");
			return (Food) idMap.get(ref);
		} catch(JSONException ex) {}

		Food ans = new Food();
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

		ans.setFoodName((String) fieldValuePairs.get("foodName"));

		ans.setIsMeat((boolean) fieldValuePairs.get("isMeat"));

		return ans;
	}

}