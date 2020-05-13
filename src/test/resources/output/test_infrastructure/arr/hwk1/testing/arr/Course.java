package hwk1.testing.arr;

import java.util.*;
import org.json.*;

public class Course {
	private Faculty instructor;
	private int numStudents;
	private ArrayList<Grade> grades;

	public Course() {
		grades = new ArrayList<>();	
	}

	public Faculty getInstructor() {
		return this.instructor;
	}

	public void setInstructor(Faculty f){
		this.instructor = f;
	}

	public int getNumStudents() {
		return this.numStudents;
	}

	public void setNumStudents(int f){
		this.numStudents = f;
	}

	public Grade getGrades(int index) {
		return this.grades.get(index);
	}

	public void setGrades(int index, Grade f) {
		this.grades.set(index, f);
	}

	public void addGrades(Grade f) {
		this.grades.add(f);
	}

	public int numGrades() {
		return this.grades.size();
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
		ans.put("type", "Course");
		idMap.put(this, idMap.size());

		JSONArray values = new JSONArray();
		JSONObject jsobj;
		JSONArray arr;

		jsobj = new JSONObject();
		jsobj.put("instructor", this.instructor.toJSON(idMap));
		values.put(jsobj);

		jsobj = new JSONObject();
		jsobj.put("numStudents", this.numStudents);
		values.put(jsobj);

		jsobj = new JSONObject();
		arr = new JSONArray();
		for(Grade target1 : grades) {
			arr.put(target1.toJSON(idMap));
		}
		jsobj.put("grades", arr);
		values.put(jsobj);

		ans.put("values", values);
		return ans;
	}
}