package hwk1.testing.arr;

import java.util.*;
import org.json.*;

public class Faculty {
	private String name;
	private ArrayList<Course> taught;

	public Faculty() {
		taught = new ArrayList<>();	
	}

	public String getName() {
		return this.name;
	}

	public void setName(String f){
		this.name = f;
	}

	public Course getTaught(int index) {
		return this.taught.get(index);
	}

	public void setTaught(int index, Course f) {
		this.taught.set(index, f);
	}

	public void addTaught(Course f) {
		this.taught.add(f);
	}

	public int numTaught() {
		return this.taught.size();
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
		ans.put("type", "Faculty");
		idMap.put(this, idMap.size());

		JSONArray values = new JSONArray();
		JSONObject jsobj;
		JSONArray arr;

		jsobj = new JSONObject();
		jsobj.put("name", this.name);
		values.put(jsobj);

		jsobj = new JSONObject();
		arr = new JSONArray();
		for(Course target1 : taught) {
			arr.put(target1.toJSON(idMap));
		}
		jsobj.put("taught", arr);
		values.put(jsobj);

		ans.put("values", values);
		return ans;
	}
}