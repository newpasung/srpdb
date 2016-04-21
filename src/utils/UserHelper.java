package utils;

import java.util.Iterator;
import java.util.Set;

import main.JsonParsor;
import net.sf.json.JSONObject;

import org.bson.Document;

public class UserHelper {

	/* ��ӡ���п��õ��û��ֶ� */
	public static String printAll(Document user) {
		if (user==null) {
			return JsonParsor.fail("��Чuser����");
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
