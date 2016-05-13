package srp.mongodb.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.bson.Document;

import srp.mongodb.main.JsonParsor;

import com.mongodb.client.FindIterable;

public class IniProductHelper {

	public static String printAll(FindIterable<Document> findIterable) {
		JSONObject data = new JSONObject();
		JSONArray productArray = new JSONArray();
		for (Document document : findIterable) {
			productArray.add(JsonParsor.filterOid(document.toJson()));
		}
		data.put("products", productArray);
		return JsonParsor.succeed(data);
	}

}
