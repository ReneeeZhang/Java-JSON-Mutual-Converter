package hwk1.testing.simplearray;

import java.util.*;
import org.json.*;

public class Deserializer {
	public static Test readTest(JSONObject js) throws JSONException {
		Map<Integer, Object> idMap = new HashMap<>();
		return readTest(js, idMap);
	}

	private static Test readTest(JSONObject js, Map<Integer, Object> idMap) throws JSONException {
		try {
			int ref = js.getInt("ref");
			return (Test) idMap.get(ref);
		} catch(JSONException ex) {}

		Test ans = new Test();
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

		arr1 = (JSONArray) fieldValuePairs.get("arr");
		for(int i1 = 0; i1 < arr1.length(); i1++) {
			ans.addArr((int) arr1.get(i1));
		}
		
		return ans;
	}

}