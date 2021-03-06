package hwk1.testing.empty;

import java.util.*;
import org.json.*;

public class Empty {

	public Empty() {	
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
		ans.put("type", "Empty");
		idMap.put(this, idMap.size());

		JSONArray values = new JSONArray();
		JSONObject jsobj;
		JSONArray arr;

		ans.put("values", values);
		return ans;
	}
}