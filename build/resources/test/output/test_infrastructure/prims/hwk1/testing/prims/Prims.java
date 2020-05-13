package hwk1.testing.prims;

import java.util.*;
import org.json.*;

public class Prims {
	private int x;
	private boolean isAwesome;
	private byte ateBits;
	private float boat;
	private double trouble;
	private char isntPronouncedLikeCare;
	private long waysAway;
	private short stackOfPancakes;

	public Prims() {	
	}

	public int getX() {
		return this.x;
	}

	public void setX(int f){
		this.x = f;
	}

	public boolean getIsAwesome() {
		return this.isAwesome;
	}

	public void setIsAwesome(boolean f){
		this.isAwesome = f;
	}

	public byte getAteBits() {
		return this.ateBits;
	}

	public void setAteBits(byte f){
		this.ateBits = f;
	}

	public float getBoat() {
		return this.boat;
	}

	public void setBoat(float f){
		this.boat = f;
	}

	public double getTrouble() {
		return this.trouble;
	}

	public void setTrouble(double f){
		this.trouble = f;
	}

	public char getIsntPronouncedLikeCare() {
		return this.isntPronouncedLikeCare;
	}

	public void setIsntPronouncedLikeCare(char f){
		this.isntPronouncedLikeCare = f;
	}

	public long getWaysAway() {
		return this.waysAway;
	}

	public void setWaysAway(long f){
		this.waysAway = f;
	}

	public short getStackOfPancakes() {
		return this.stackOfPancakes;
	}

	public void setStackOfPancakes(short f){
		this.stackOfPancakes = f;
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
		ans.put("type", "Prims");
		idMap.put(this, idMap.size());

		JSONArray values = new JSONArray();
		JSONObject jsobj;
		JSONArray arr;

		jsobj = new JSONObject();
		jsobj.put("x", this.x);
		values.put(jsobj);

		jsobj = new JSONObject();
		jsobj.put("isAwesome", this.isAwesome);
		values.put(jsobj);

		jsobj = new JSONObject();
		jsobj.put("ateBits", this.ateBits);
		values.put(jsobj);

		jsobj = new JSONObject();
		jsobj.put("boat", this.boat);
		values.put(jsobj);

		jsobj = new JSONObject();
		jsobj.put("trouble", this.trouble);
		values.put(jsobj);

		jsobj = new JSONObject();
		jsobj.put("isntPronouncedLikeCare", this.isntPronouncedLikeCare);
		values.put(jsobj);

		jsobj = new JSONObject();
		jsobj.put("waysAway", this.waysAway);
		values.put(jsobj);

		jsobj = new JSONObject();
		jsobj.put("stackOfPancakes", this.stackOfPancakes);
		values.put(jsobj);

		ans.put("values", values);
		return ans;
	}
}