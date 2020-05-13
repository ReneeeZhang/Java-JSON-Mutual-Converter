package hwk1.testing.crazyarray;

import java.util.*;
import org.json.*;

public class LotsOfArray {
	private ArrayList<Integer> x;
	private ArrayList<Collection<Boolean>> isAwesome;
	private ArrayList<Collection<Collection<Byte>>> ateBits;
	private ArrayList<Collection<Collection<Collection<Float>>>> boat;
	private ArrayList<Double> trouble;
	private ArrayList<Collection<Character>> isntPronouncedLikeCare;
	private ArrayList<Long> waysAway;
	private ArrayList<Collection<Short>> stackOfPancakes;

	public LotsOfArray() {
		x = new ArrayList<>();
		isAwesome = new ArrayList<>();
		ateBits = new ArrayList<>();
		boat = new ArrayList<>();
		trouble = new ArrayList<>();
		isntPronouncedLikeCare = new ArrayList<>();
		waysAway = new ArrayList<>();
		stackOfPancakes = new ArrayList<>();	
	}

	public int getX(int index) {
		return this.x.get(index);
	}

	public void setX(int index, int f) {
		this.x.set(index, f);
	}

	public void addX(int f) {
		this.x.add(f);
	}

	public int numX() {
		return this.x.size();
	}

	public Collection<Boolean> getIsAwesome(int index) {
		return this.isAwesome.get(index);
	}

	public void setIsAwesome(int index, Collection<Boolean> f) {
		this.isAwesome.set(index, f);
	}

	public void addIsAwesome(Collection<Boolean> f) {
		this.isAwesome.add(f);
	}

	public int numIsAwesome() {
		return this.isAwesome.size();
	}

	public Collection<Collection<Byte>> getAteBits(int index) {
		return this.ateBits.get(index);
	}

	public void setAteBits(int index, Collection<Collection<Byte>> f) {
		this.ateBits.set(index, f);
	}

	public void addAteBits(Collection<Collection<Byte>> f) {
		this.ateBits.add(f);
	}

	public int numAteBits() {
		return this.ateBits.size();
	}

	public Collection<Collection<Collection<Float>>> getBoat(int index) {
		return this.boat.get(index);
	}

	public void setBoat(int index, Collection<Collection<Collection<Float>>> f) {
		this.boat.set(index, f);
	}

	public void addBoat(Collection<Collection<Collection<Float>>> f) {
		this.boat.add(f);
	}

	public int numBoat() {
		return this.boat.size();
	}

	public double getTrouble(int index) {
		return this.trouble.get(index);
	}

	public void setTrouble(int index, double f) {
		this.trouble.set(index, f);
	}

	public void addTrouble(double f) {
		this.trouble.add(f);
	}

	public int numTrouble() {
		return this.trouble.size();
	}

	public Collection<Character> getIsntPronouncedLikeCare(int index) {
		return this.isntPronouncedLikeCare.get(index);
	}

	public void setIsntPronouncedLikeCare(int index, Collection<Character> f) {
		this.isntPronouncedLikeCare.set(index, f);
	}

	public void addIsntPronouncedLikeCare(Collection<Character> f) {
		this.isntPronouncedLikeCare.add(f);
	}

	public int numIsntPronouncedLikeCare() {
		return this.isntPronouncedLikeCare.size();
	}

	public long getWaysAway(int index) {
		return this.waysAway.get(index);
	}

	public void setWaysAway(int index, long f) {
		this.waysAway.set(index, f);
	}

	public void addWaysAway(long f) {
		this.waysAway.add(f);
	}

	public int numWaysAway() {
		return this.waysAway.size();
	}

	public Collection<Short> getStackOfPancakes(int index) {
		return this.stackOfPancakes.get(index);
	}

	public void setStackOfPancakes(int index, Collection<Short> f) {
		this.stackOfPancakes.set(index, f);
	}

	public void addStackOfPancakes(Collection<Short> f) {
		this.stackOfPancakes.add(f);
	}

	public int numStackOfPancakes() {
		return this.stackOfPancakes.size();
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
		ans.put("type", "LotsOfArray");
		idMap.put(this, idMap.size());

		JSONArray values = new JSONArray();
		JSONObject jsobj;
		JSONArray arr;

		jsobj = new JSONObject();
		arr = new JSONArray(this.x);
		jsobj.put("x", arr);
		values.put(jsobj);

		jsobj = new JSONObject();
		arr = new JSONArray(this.isAwesome);
		jsobj.put("isAwesome", arr);
		values.put(jsobj);

		jsobj = new JSONObject();
		arr = new JSONArray(this.ateBits);
		jsobj.put("ateBits", arr);
		values.put(jsobj);

		jsobj = new JSONObject();
		arr = new JSONArray(this.boat);
		jsobj.put("boat", arr);
		values.put(jsobj);

		jsobj = new JSONObject();
		arr = new JSONArray(this.trouble);
		jsobj.put("trouble", arr);
		values.put(jsobj);

		jsobj = new JSONObject();
		arr = new JSONArray(this.isntPronouncedLikeCare);
		jsobj.put("isntPronouncedLikeCare", arr);
		values.put(jsobj);

		jsobj = new JSONObject();
		arr = new JSONArray(this.waysAway);
		jsobj.put("waysAway", arr);
		values.put(jsobj);

		jsobj = new JSONObject();
		arr = new JSONArray(this.stackOfPancakes);
		jsobj.put("stackOfPancakes", arr);
		values.put(jsobj);

		ans.put("values", values);
		return ans;
	}
}