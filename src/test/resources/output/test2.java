import java.util.*;
import org.json.*;

public class Test {
	private ArrayList<Collection<Collection<GermanName>>> x;

	public Test() {
		x = new ArrayList<>();	
	}

	public Collection<Collection<GermanName>> getX(int index) {
		return this.x.get(index);
	}

	public void setX(int index, Collection<Collection<GermanName>> f) {
		this.x.set(index, f);
	}

	public void addX(Collection<Collection<GermanName>> f) {
		this.x.add(f);
	}

	public int numX() {
		return this.x.size();
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
		arr = new JSONArray();
		for(Collection<Collection<GermanName>> target1 : x) {
			JSONArray arr1 = new JSONArray();
			for(Collection<GermanName> target2 : target1) {
				JSONArray arr2 = new JSONArray();
				for(GermanName target3 : target2) {
					arr2.put(target3.toJSON(idMap));
				}
				arr1.put(arr2);
			}
			arr.put(arr1);
		}
		jsobj.put("x", arr);
		values.put(jsobj);

		ans.put("values", values);
		return ans;
	}
}