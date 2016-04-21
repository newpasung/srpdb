package utils;

import main.JsonParsor;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.bson.Document;

import com.mongodb.client.FindIterable;

public class IniProductHelper {

	public static String printAll(FindIterable<Document> findIterable) {
		JSONObject data = new JSONObject();
		JSONArray productArray = new JSONArray();
		for (Document document : findIterable) {
			productArray.add(document.toJson());
		}
		data.put("products", productArray);
		return JsonParsor.succeed(data);
	}

}
