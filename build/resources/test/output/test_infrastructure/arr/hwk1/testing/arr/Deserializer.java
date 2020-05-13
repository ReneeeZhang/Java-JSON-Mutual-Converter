package hwk1.testing.arr;

import java.util.*;
import org.json.*;

public class Deserializer {
	public static Course readCourse(JSONObject js) throws JSONException {
		Map<Integer, Object> idMap = new HashMap<>();
		return readCourse(js, idMap);
	}

	private static Course readCourse(JSONObject js, Map<Integer, Object> idMap) throws JSONException {
		try {
			int ref = js.getInt("ref");
			return (Course) idMap.get(ref);
		} catch(JSONException ex) {}

		Course ans = new Course();
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

		obj = (JSONObject) fieldValuePairs.get("instructor");
		ans.setInstructor(readFaculty(obj, idMap));

		ans.setNumStudents((int) fieldValuePairs.get("numStudents"));

		arr1 = (JSONArray) fieldValuePairs.get("grades");
		for(int i1 = 0; i1 < arr1.length(); i1++) {
			obj = arr1.getJSONObject(i1);
			ans.addGrades(readGrade(obj, idMap));
		}
		
		return ans;
	}

	public static Office readOffice(JSONObject js) throws JSONException {
		Map<Integer, Object> idMap = new HashMap<>();
		return readOffice(js, idMap);
	}

	private static Office readOffice(JSONObject js, Map<Integer, Object> idMap) throws JSONException {
		try {
			int ref = js.getInt("ref");
			return (Office) idMap.get(ref);
		} catch(JSONException ex) {}

		Office ans = new Office();
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

		obj = (JSONObject) fieldValuePairs.get("occupant");
		ans.setOccupant(readFaculty(obj, idMap));

		ans.setNumber((int) fieldValuePairs.get("number"));

		ans.setBuilding((String) fieldValuePairs.get("building"));

		return ans;
	}

	public static Faculty readFaculty(JSONObject js) throws JSONException {
		Map<Integer, Object> idMap = new HashMap<>();
		return readFaculty(js, idMap);
	}

	private static Faculty readFaculty(JSONObject js, Map<Integer, Object> idMap) throws JSONException {
		try {
			int ref = js.getInt("ref");
			return (Faculty) idMap.get(ref);
		} catch(JSONException ex) {}

		Faculty ans = new Faculty();
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

		ans.setName((String) fieldValuePairs.get("name"));

		arr1 = (JSONArray) fieldValuePairs.get("taught");
		for(int i1 = 0; i1 < arr1.length(); i1++) {
			obj = arr1.getJSONObject(i1);
			ans.addTaught(readCourse(obj, idMap));
		}
		
		return ans;
	}

	public static Grade readGrade(JSONObject js) throws JSONException {
		Map<Integer, Object> idMap = new HashMap<>();
		return readGrade(js, idMap);
	}

	private static Grade readGrade(JSONObject js, Map<Integer, Object> idMap) throws JSONException {
		try {
			int ref = js.getInt("ref");
			return (Grade) idMap.get(ref);
		} catch(JSONException ex) {}

		Grade ans = new Grade();
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

		obj = (JSONObject) fieldValuePairs.get("course");
		ans.setCourse(readCourse(obj, idMap));

		ans.setStudent((String) fieldValuePairs.get("student"));

		ans.setGrade((double) fieldValuePairs.get("grade"));

		return ans;
	}

}