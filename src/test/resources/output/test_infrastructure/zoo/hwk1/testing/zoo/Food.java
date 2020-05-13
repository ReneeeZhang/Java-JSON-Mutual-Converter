package hwk1.testing.zoo;

import java.util.*;
import org.json.*;

public class Food {
	private String foodName;
	private boolean isMeat;

	public Food() {	
	}

	public String getFoodName() {
		return this.foodName;
	}

	public void setFoodName(String f){
		this.foodName = f;
	}

	public boolean getIsMeat() {
		return this.isMeat;
	}

	public void setIsMeat(boolean f){
		this.isMeat = f;
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
		ans.put("type", "Food");
		idMap.put(this, idMap.size());

		JSONArray values = new JSONArray();
		JSONObject jsobj;
		JSONArray arr;

		jsobj = new JSONObject();
		jsobj.put("foodName", this.foodName);
		values.put(jsobj);

		jsobj = new JSONObject();
		jsobj.put("isMeat", this.isMeat);
		values.put(jsobj);

		ans.put("values", values);
		return ans;
	}
}