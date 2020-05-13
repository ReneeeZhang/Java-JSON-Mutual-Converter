package edu.duke.ece651.hwk1.data;

import java.util.*;
import org.json.*;

public class Test {
	private int car;

	public Test() {	
	}

	public int getCar() {
		return this.car;
	}

	public void setCar(int f){
		this.car = f;
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
		ans.put("type", "Test");
		idMap.put(this, idMap.size());

		JSONArray values = new JSONArray();
		JSONObject jsobj;
		JSONArray arr;

		jsobj = new JSONObject();
		jsobj.put("car", this.car);
		values.put(jsobj);

		ans.put("values", values);
		return ans;
	}
}