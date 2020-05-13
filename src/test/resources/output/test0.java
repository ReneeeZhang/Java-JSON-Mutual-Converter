import java.util.*;
import org.json.*;

public class Test {
	private ArrayList<ChineseName> x;

	public Test() {
		x = new ArrayList<>();	
	}

	public ChineseName getX(int index) {
		return this.x.get(index);
	}

	public void setX(int index, ChineseName f) {
		this.x.set(index, f);
	}

	public void addX(ChineseName f) {
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
		for(ChineseName target1 : x) {
			arr.put(target1.toJSON(idMap));
		}
		jsobj.put("x", arr);
		values.put(jsobj);

		ans.put("values", values);
		return ans;
	}
}