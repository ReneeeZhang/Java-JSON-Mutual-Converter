package hwk1.testing.zoo;

import java.util.*;
import org.json.*;

public class Keeper {
	private String firstName;
	private String lastName;
	private double salary;
	private ArrayList<AnimalPen> myPens;

	public Keeper() {
		myPens = new ArrayList<>();	
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String f){
		this.firstName = f;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String f){
		this.lastName = f;
	}

	public double getSalary() {
		return this.salary;
	}

	public void setSalary(double f){
		this.salary = f;
	}

	public AnimalPen getMyPens(int index) {
		return this.myPens.get(index);
	}

	public void setMyPens(int index, AnimalPen f) {
		this.myPens.set(index, f);
	}

	public void addMyPens(AnimalPen f) {
		this.myPens.add(f);
	}

	public int numMyPens() {
		return this.myPens.size();
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
		ans.put("type", "Keeper");
		idMap.put(this, idMap.size());

		JSONArray values = new JSONArray();
		JSONObject jsobj;
		JSONArray arr;

		jsobj = new JSONObject();
		jsobj.put("firstName", this.firstName);
		values.put(jsobj);

		jsobj = new JSONObject();
		jsobj.put("lastName", this.lastName);
		values.put(jsobj);

		jsobj = new JSONObject();
		jsobj.put("salary", this.salary);
		values.put(jsobj);

		jsobj = new JSONObject();
		arr = new JSONArray();
		for(AnimalPen target1 : myPens) {
			arr.put(target1.toJSON(idMap));
		}
		jsobj.put("myPens", arr);
		values.put(jsobj);

		ans.put("values", values);
		return ans;
	}
}