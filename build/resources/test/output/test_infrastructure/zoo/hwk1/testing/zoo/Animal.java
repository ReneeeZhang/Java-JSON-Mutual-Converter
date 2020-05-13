package hwk1.testing.zoo;

import java.util.*;
import org.json.*;

public class Animal {
	private String myName;
	private String species;
	private int age;
	private ArrayList<Collection<Food>> foodsByPreference;

	public Animal() {
		foodsByPreference = new ArrayList<>();	
	}

	public String getMyName() {
		return this.myName;
	}

	public void setMyName(String f){
		this.myName = f;
	}

	public String getSpecies() {
		return this.species;
	}

	public void setSpecies(String f){
		this.species = f;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int f){
		this.age = f;
	}

	public Collection<Food> getFoodsByPreference(int index) {
		return this.foodsByPreference.get(index);
	}

	public void setFoodsByPreference(int index, Collection<Food> f) {
		this.foodsByPreference.set(index, f);
	}

	public void addFoodsByPreference(Collection<Food> f) {
		this.foodsByPreference.add(f);
	}

	public int numFoodsByPreference() {
		return this.foodsByPreference.size();
	}


	public JSONObject toJSON() throws JSONException {
		Map<Object, Integer> idMap = new HashMap<>();
		return toJSON(idMap);
	}

	public JSONObject toJSON(Map<Object, Integer> idMap) throws JSONException {
		JSONObject ans = new JSONObject();
		if(idMap.containsKey(this)){
			ans.put("ref", idMap.get(this));
			return ans;
		}

		ans.put("id", idMap.size());
		ans.put("type", "Animal");
		idMap.put(this, idMap.size());

		JSONArray values = new JSONArray();
		JSONObject jsobj;
		JSONArray arr;

		jsobj = new JSONObject();
		jsobj.put("myName", this.myName);
		values.put(jsobj);

		jsobj = new JSONObject();
		jsobj.put("species", this.species);
		values.put(jsobj);

		jsobj = new JSONObject();
		jsobj.put("age", this.age);
		values.put(jsobj);

		jsobj = new JSONObject();
		arr = new JSONArray();
		for(Collection<Food> target1 : foodsByPreference) {
			JSONArray arr1 = new JSONArray();
			for(Food target2 : target1) {
				arr1.put(target2.toJSON(idMap));
			}
			arr.put(arr1);
		}
		jsobj.put("foodsByPreference", arr);
		values.put(jsobj);

		ans.put("values", values);
		return ans;
	}
}