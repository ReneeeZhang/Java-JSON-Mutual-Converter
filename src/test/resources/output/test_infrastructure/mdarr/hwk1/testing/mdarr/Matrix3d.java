package hwk1.testing.mdarr;

import java.util.*;
import org.json.*;

public class Matrix3d {
	private ArrayList<Collection<Collection<Integer>>> data;

	public Matrix3d() {
		data = new ArrayList<>();	
	}

	public Collection<Collection<Integer>> getData(int index) {
		return this.data.get(index);
	}

	public void setData(int index, Collection<Collection<Integer>> f) {
		this.data.set(index, f);
	}

	public void addData(Collection<Collection<Integer>> f) {
		this.data.add(f);
	}

	public int numData() {
		return this.data.size();
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
		ans.put("type", "Matrix3d");
		idMap.put(this, idMap.size());

		JSONArray values = new JSONArray();
		JSONObject jsobj;
		JSONArray arr;

		jsobj = new JSONObject();
		arr = new JSONArray(this.data);
		jsobj.put("data", arr);
		values.put(jsobj);

		ans.put("values", values);
		return ans;
	}
}