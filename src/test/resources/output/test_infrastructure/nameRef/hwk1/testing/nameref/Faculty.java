package hwk1.testing.nameref;

import java.util.*;
import org.json.*;

public class Faculty {
	private String name;
	private ArrayList<Course> courses;

	public Faculty() {
		courses = new ArrayList<>();	
	}

	public String getName() {
		return this.name;
	}

	public void setName(String f){
		this.name = f;
	}

	public Course getCourses(int index) {
		return this.courses.get(index);
	}

	public void setCourses(int index, Course f) {
		this.courses.set(index, f);
	}

	public void addCourses(Course f) {
		this.courses.add(f);
	}

	public int numCourses() {
		return this.courses.size();
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
		for(Course target1 : courses) {
			arr.put(target1.toJSON(idMap));
		}
		jsobj.put("courses", arr);
		values.put(jsobj);

		ans.put("values", values);
		return ans;
	}
}