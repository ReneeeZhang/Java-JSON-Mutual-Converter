package edu.duke.ece651test;

import java.util.*;
import org.json.*;

public class Faculty {
	private String name;

	public Faculty() {	
	}

	public String getName() {
		return this.name;
	}

	public void setName(String f){
		this.name = f;
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
		ans.put("type", "Faculty");
		idMap.put(this, idMap.size());

		JSONArray values = new JSONArray();
		JSONObject jsobj;
		JSONArray arr;

		jsobj = new JSONObject();
		jsobj.put("name", this.name);
		values.put(jsobj);

		ans.put("values", values);
		return ans;
	}
}