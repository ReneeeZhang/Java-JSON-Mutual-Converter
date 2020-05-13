package hwk1.testing.simplearray;

import java.util.*;
import org.json.*;

public class Test {
	private ArrayList<Integer> arr;

	public Test() {
		arr = new ArrayList<>();	
	}

	public int getArr(int index) {
		return this.arr.get(index);
	}

	public void setArr(int index, int f) {
		this.arr.set(index, f);
	}

	public void addArr(int f) {
		this.arr.add(f);
	}

	public int numArr() {
		return this.arr.size();
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
		arr = new JSONArray(this.arr);
		jsobj.put("arr", arr);
		values.put(jsobj);

		ans.put("values", values);
		return ans;
	}
}