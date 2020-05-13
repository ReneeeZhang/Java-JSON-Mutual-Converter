package edu.duke.ece651test;

import java.util.*;
import org.json.*;

public class Office {
	private Faculty occupant;
	private int number;
	private String building;

	public Office() {	
	}

	public Faculty getOccupant() {
		return this.occupant;
	}

	public void setOccupant(Faculty f){
		this.occupant = f;
	}

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int f){
		this.number = f;
	}

	public String getBuilding() {
		return this.building;
	}

	public void setBuilding(String f){
		this.building = f;
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
		ans.put("type", "Office");
		idMap.put(this, idMap.size());

		JSONArray values = new JSONArray();
		JSONObject jsobj;
		JSONArray arr;

		jsobj = new JSONObject();
		jsobj.put("occupant", this.occupant.toJSON(idMap));
		values.put(jsobj);

		jsobj = new JSONObject();
		jsobj.put("number", this.number);
		values.put(jsobj);

		jsobj = new JSONObject();
		jsobj.put("building", this.building);
		values.put(jsobj);

		ans.put("values", values);
		return ans;
	}
}