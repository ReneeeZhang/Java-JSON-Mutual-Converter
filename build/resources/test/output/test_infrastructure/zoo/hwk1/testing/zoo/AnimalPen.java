package hwk1.testing.zoo;

import java.util.*;
import org.json.*;

public class AnimalPen {
	private float xloc;
	private float yloc;
	private float width;
	private float length;
	private float height;
	private ArrayList<Animal> animals;
	private String signText;

	public AnimalPen() {
		animals = new ArrayList<>();	
	}

	public float getXloc() {
		return this.xloc;
	}

	public void setXloc(float f){
		this.xloc = f;
	}

	public float getYloc() {
		return this.yloc;
	}

	public void setYloc(float f){
		this.yloc = f;
	}

	public float getWidth() {
		return this.width;
	}

	public void setWidth(float f){
		this.width = f;
	}

	public float getLength() {
		return this.length;
	}

	public void setLength(float f){
		this.length = f;
	}

	public float getHeight() {
		return this.height;
	}

	public void setHeight(float f){
		this.height = f;
	}

	public Animal getAnimals(int index) {
		return this.animals.get(index);
	}

	public void setAnimals(int index, Animal f) {
		this.animals.set(index, f);
	}

	public void addAnimals(Animal f) {
		this.animals.add(f);
	}

	public int numAnimals() {
		return this.animals.size();
	}

	public String getSignText() {
		return this.signText;
	}

	public void setSignText(String f){
		this.signText = f;
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
		ans.put("type", "AnimalPen");
		idMap.put(this, idMap.size());

		JSONArray values = new JSONArray();
		JSONObject jsobj;
		JSONArray arr;

		jsobj = new JSONObject();
		jsobj.put("xloc", this.xloc);
		values.put(jsobj);

		jsobj = new JSONObject();
		jsobj.put("yloc", this.yloc);
		values.put(jsobj);

		jsobj = new JSONObject();
		jsobj.put("width", this.width);
		values.put(jsobj);

		jsobj = new JSONObject();
		jsobj.put("length", this.length);
		values.put(jsobj);

		jsobj = new JSONObject();
		jsobj.put("height", this.height);
		values.put(jsobj);

		jsobj = new JSONObject();
		arr = new JSONArray();
		for(Animal target1 : animals) {
			arr.put(target1.toJSON(idMap));
		}
		jsobj.put("animals", arr);
		values.put(jsobj);

		jsobj = new JSONObject();
		jsobj.put("signText", this.signText);
		values.put(jsobj);

		ans.put("values", values);
		return ans;
	}
}