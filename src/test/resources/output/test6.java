import java.util.*;
import org.json.*;

public class Test {
	private int _car;

	public Test() {	
	}

	public int get_car() {
		return this._car;
	}

	public void set_car(int f){
		this._car = f;
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
		jsobj.put("_car", this._car);
		values.put(jsobj);

		ans.put("values", values);
		return ans;
	}
}