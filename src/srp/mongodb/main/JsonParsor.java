package srp.mongodb.main;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.bson.Document;

import srp.mongodb.utils.StatusCode;

import com.mongodb.client.FindIterable;


public class JsonParsor {

	public static String fail(int statusCode, String failMsg) {
		JSONObject result = new JSONObject();
		result.put("status", statusCode);
		result.put("failmsg", failMsg);
		return result.toString();
	}

	public static String succeed(JSONObject data) {
		JSONObject result = new JSONObject();
		result.put("status", 200);
		result.put("data", data);
		return result.toString();
	}

	public static String succeed(String msg) {
		JSONObject result = new JSONObject();
		result.put("status", 200);
		JSONObject data = new JSONObject();
		data.put("message", msg);
		result.put("data", data);
		return result.toString();
	}

	public static String succeedPrintAll(String fieldName,
			FindIterable<Document> findIterable) {
		JSONObject data = new JSONObject();
		JSONArray productArray = new JSONArray();
		for (Document document : findIterable) {
			productArray.add(filterOid(document.toJson()));
		}
		data.put(fieldName, productArray);
		return JsonParsor.succeed(data);
	}

	public static String succceedPrintOne(String fieldName,
			FindIterable<Document> findIterable) {
		JSONObject data = new JSONObject();
		data.put(fieldName, filterOid(findIterable.first().toJson()));
		return JsonParsor.succeed(data);
	}

	public static String succeedPrintAll(String fieldName, Document document) {
		JSONObject data = new JSONObject();
		data.put(fieldName, JSONObject.fromObject(document.toJson()));
		return JsonParsor.succeed(data);
	}

	public static String succeed() {
		return succeed("³É¹¦");
	}

	public static String fail() {
		return fail(StatusCode.FAILE, "²Ù×÷Ê§°Ü");
	}

	public static String filterOid(String jsonstr_with_oid) {
		try {
			JSONObject result = JSONObject.fromObject(jsonstr_with_oid);
			if (result.containsKey("_id")) {
				JSONObject id = result.getJSONObject("_id");
				String str_id = id.getString("$oid");
				result.put("_id", str_id);
			}
			return result.toString();
		} catch (JSONException e) {
			return jsonstr_with_oid;
		}
	}

}
