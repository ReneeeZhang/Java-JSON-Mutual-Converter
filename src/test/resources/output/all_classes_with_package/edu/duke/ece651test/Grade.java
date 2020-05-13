package edu.duke.ece651test;

import java.util.*;
import org.json.*;

public class Grade {
	private Course course;
	private String student;
	private double grade;

	public Grade() {	
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course f){
		this.course = f;
	}

	public String getStudent() {
		return this.student;
	}

	public void setStudent(String f){
		this.student = f;
	}

	public double getGrade() {
		return this.grade;
	}

	public void setGrade(double f){
		this.grade = f;
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
		ans.put("type", "Grade");
		idMap.put(this, idMap.size());

		JSONArray values = new JSONArray();
		JSONObject jsobj;
		JSONArray arr;

		jsobj = new JSONObject();
		jsobj.put("course", this.course.toJSON(idMap));
		values.put(jsobj);

		jsobj = new JSONObject();
		jsobj.put("student", this.student);
		values.put(jsobj);

		jsobj = new JSONObject();
		jsobj.put("grade", this.grade);
		values.put(jsobj);

		ans.put("values", values);
		return ans;
	}
}