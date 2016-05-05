package srp.mongodb.utils;

import java.util.Iterator;
import java.util.Set;

import net.sf.json.JSONObject;

import org.bson.Document;

import srp.mongodb.main.JsonParsor;

public class UserHelper {

	/* 打印所有可用的用户字段 */
	public static String printAll(Document user) {
		if (user==null) {
			return JsonParsor.fail(StatusCode.INVALIDDATA, "无效user数据");
		}
		JSONObject result = new JSONObject();
		JSONObject data=new JSONObject();
		Set keys = user.keySet();
		for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			if (!key.equals(Names.DCAccount_account)
					&& !key.equals(Names.DCAccount_created_time)
					&& !key.equals(Names.DCAccount_password)) {
				data.put(key, user.get(key));
			}
		}
		result.put("user", data);
		return JsonParsor.succeed(result).toString();
	}

}
