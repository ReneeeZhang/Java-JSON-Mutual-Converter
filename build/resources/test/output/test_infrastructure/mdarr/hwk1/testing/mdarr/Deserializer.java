package hwk1.testing.mdarr;

import java.util.*;
import org.json.*;

public class Deserializer {
	public static Matrix3d readMatrix3d(JSONObject js) throws JSONException {
		Map<Integer, Object> idMap = new HashMap<>();
		return readMatrix3d(js, idMap);
	}

	private static Matrix3d readMatrix3d(JSONObject js, Map<Integer, Object> idMap) throws JSONException {
		try {
			int ref = js.getInt("ref");
			return (Matrix3d) idMap.get(ref);
		} catch(JSONException ex) {}

		Matrix3d ans = new Matrix3d();
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

		arr1 = (JSONArray) fieldValuePairs.get("data");
		for(int i1 = 0; i1 < arr1.length(); i1++) {
			Collection<Collection<Integer>> target1 = new ArrayList<>();
			JSONArray arr2 = arr1.getJSONArray(i1);
			for(int i2 = 0; i2 < arr2.length(); i2++) {
				Collection<Integer> target2 = new ArrayList<>();
				JSONArray arr3 = arr2.getJSONArray(i2);
				for(int i3 = 0; i3 < arr3.length(); i3++) {
					target2.add((int) arr3.get(i3));
				}
				target1.add(target2);
			}
			ans.addData(target1);
		}
		
		return ans;
	}

}