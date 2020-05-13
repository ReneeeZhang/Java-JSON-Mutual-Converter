package hwk1.testing.crazyarray;

import java.util.*;
import org.json.*;

public class Deserializer {
	public static LotsOfArray readLotsOfArray(JSONObject js) throws JSONException {
		Map<Integer, Object> idMap = new HashMap<>();
		return readLotsOfArray(js, idMap);
	}

	private static LotsOfArray readLotsOfArray(JSONObject js, Map<Integer, Object> idMap) throws JSONException {
		try {
			int ref = js.getInt("ref");
			return (LotsOfArray) idMap.get(ref);
		} catch(JSONException ex) {}

		LotsOfArray ans = new LotsOfArray();
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

		arr1 = (JSONArray) fieldValuePairs.get("x");
		for(int i1 = 0; i1 < arr1.length(); i1++) {
			ans.addX((int) arr1.get(i1));
		}
		
		arr1 = (JSONArray) fieldValuePairs.get("isAwesome");
		for(int i1 = 0; i1 < arr1.length(); i1++) {
			Collection<Boolean> target1 = new ArrayList<>();
			JSONArray arr2 = arr1.getJSONArray(i1);
			for(int i2 = 0; i2 < arr2.length(); i2++) {
				target1.add((boolean) arr2.get(i2));
			}
			ans.addIsAwesome(target1);
		}
		
		arr1 = (JSONArray) fieldValuePairs.get("ateBits");
		for(int i1 = 0; i1 < arr1.length(); i1++) {
			Collection<Collection<Byte>> target1 = new ArrayList<>();
			JSONArray arr2 = arr1.getJSONArray(i1);
			for(int i2 = 0; i2 < arr2.length(); i2++) {
				Collection<Byte> target2 = new ArrayList<>();
				JSONArray arr3 = arr2.getJSONArray(i2);
				for(int i3 = 0; i3 < arr3.length(); i3++) {
					target2.add((byte) arr3.get(i3));
				}
				target1.add(target2);
			}
			ans.addAteBits(target1);
		}
		
		arr1 = (JSONArray) fieldValuePairs.get("boat");
		for(int i1 = 0; i1 < arr1.length(); i1++) {
			Collection<Collection<Collection<Float>>> target1 = new ArrayList<>();
			JSONArray arr2 = arr1.getJSONArray(i1);
			for(int i2 = 0; i2 < arr2.length(); i2++) {
				Collection<Collection<Float>> target2 = new ArrayList<>();
				JSONArray arr3 = arr2.getJSONArray(i2);
				for(int i3 = 0; i3 < arr3.length(); i3++) {
					Collection<Float> target3 = new ArrayList<>();
					JSONArray arr4 = arr3.getJSONArray(i3);
					for(int i4 = 0; i4 < arr4.length(); i4++) {
						target3.add((float) arr4.get(i4));
					}
					target2.add(target3);
				}
				target1.add(target2);
			}
			ans.addBoat(target1);
		}
		
		arr1 = (JSONArray) fieldValuePairs.get("trouble");
		for(int i1 = 0; i1 < arr1.length(); i1++) {
			ans.addTrouble((double) arr1.get(i1));
		}
		
		arr1 = (JSONArray) fieldValuePairs.get("isntPronouncedLikeCare");
		for(int i1 = 0; i1 < arr1.length(); i1++) {
			Collection<Character> target1 = new ArrayList<>();
			JSONArray arr2 = arr1.getJSONArray(i1);
			for(int i2 = 0; i2 < arr2.length(); i2++) {
				target1.add((char) arr2.get(i2));
			}
			ans.addIsntPronouncedLikeCare(target1);
		}
		
		arr1 = (JSONArray) fieldValuePairs.get("waysAway");
		for(int i1 = 0; i1 < arr1.length(); i1++) {
			ans.addWaysAway((long) arr1.get(i1));
		}
		
		arr1 = (JSONArray) fieldValuePairs.get("stackOfPancakes");
		for(int i1 = 0; i1 < arr1.length(); i1++) {
			Collection<Short> target1 = new ArrayList<>();
			JSONArray arr2 = arr1.getJSONArray(i1);
			for(int i2 = 0; i2 < arr2.length(); i2++) {
				target1.add((short) arr2.get(i2));
			}
			ans.addStackOfPancakes(target1);
		}
		
		return ans;
	}

}