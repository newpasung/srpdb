package srp.db.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public abstract class CommonModel {

	public static String parseString(JSONObject jsonObject, String name) {
		if (jsonObject.containsKey(name)) {
			return jsonObject.getString(name);
		} else {
			return "";
		}
	}

	public static int parseInt(JSONObject jsonObject, String name) {
		if (jsonObject.containsKey(name)) {
			return jsonObject.getInt(name);
		} else {
			return 0;
		}
	}

	public static Map<String, String> parseMap(JSONObject jsonObject, String key)
			throws JSONException {
		if (jsonObject.containsKey(key)) {
			JSONObject mapData = jsonObject.getJSONObject(key);
			Iterator iterator = mapData.keys();
			Map<String, String> result = new HashMap<String, String>();
			while (iterator.hasNext()) {
				String name = (String) iterator.next();
				result.put(name, mapData.getString(name));
			}
			return result;
		} else {
			return null;
		}
	}

	public static List<String> parseList(JSONObject jsonObject, String key)
			throws JSONException {
		if (jsonObject.containsKey(key)) {
			List<String> result = new ArrayList<>();
			JSONArray array = jsonObject.getJSONArray(key);
			for (int i = 0; i < array.size(); i++) {
				result.add(array.getString(i));
			}
			return result;
		} else {
			return null;
		}
	}

}
