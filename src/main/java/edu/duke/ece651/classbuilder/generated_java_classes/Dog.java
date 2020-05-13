package edu.duke.ece651.classbuilder.generated_java_classes;

import java.util.*;
import org.json.*;

public class Dog {
	private String name;
	private Person owner;

	public Dog() {	
	}

	public String getName() {
		return this.name;
	}

	public void setName(String f){
		this.name = f;
	}

	public Person getOwner() {
		return this.owner;
	}

	public void setOwner(Person f){
		this.owner = f;
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
		ans.put("type", "Dog");
		idMap.put(this, idMap.size());

		JSONArray values = new JSONArray();
		JSONObject jsobj;
		JSONArray arr;

		jsobj = new JSONObject();
		jsobj.put("name", this.name);
		values.put(jsobj);

		jsobj = new JSONObject();
		jsobj.put("owner", this.owner.toJSON(idMap));
		values.put(jsobj);

		ans.put("values", values);
		return ans;
	}
}
