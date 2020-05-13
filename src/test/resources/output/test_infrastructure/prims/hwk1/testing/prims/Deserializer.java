package hwk1.testing.prims;

import java.util.*;
import org.json.*;

public class Deserializer {
	public static Prims readPrims(JSONObject js) throws JSONException {
		Map<Integer, Object> idMap = new HashMap<>();
		return readPrims(js, idMap);
	}

	private static Prims readPrims(JSONObject js, Map<Integer, Object> idMap) throws JSONException {
		try {
			int ref = js.getInt("ref");
			return (Prims) idMap.get(ref);
		} catch(JSONException ex) {}

		Prims ans = new Prims();
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

		ans.setX((int) fieldValuePairs.get("x"));

		ans.setIsAwesome((boolean) fieldValuePairs.get("isAwesome"));

		ans.setAteBits((byte) fieldValuePairs.get("ateBits"));

		ans.setBoat((float) fieldValuePairs.get("boat"));

		ans.setTrouble((double) fieldValuePairs.get("trouble"));

		ans.setIsntPronouncedLikeCare((char) fieldValuePairs.get("isntPronouncedLikeCare"));

		ans.setWaysAway((long) fieldValuePairs.get("waysAway"));

		ans.setStackOfPancakes((short) fieldValuePairs.get("stackOfPancakes"));

		return ans;
	}

}