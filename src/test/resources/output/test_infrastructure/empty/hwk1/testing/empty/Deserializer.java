package hwk1.testing.empty;

import java.util.*;
import org.json.*;

public class Deserializer {
	public static Empty readEmpty(JSONObject js) throws JSONException {
		Map<Integer, Object> idMap = new HashMap<>();
		return readEmpty(js, idMap);
	}

	private static Empty readEmpty(JSONObject js, Map<Integer, Object> idMap) throws JSONException {
		try {
			int ref = js.getInt("ref");
			return (Empty) idMap.get(ref);
		} catch(JSONException ex) {}

		Empty ans = new Empty();
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

		return ans;
	}

}