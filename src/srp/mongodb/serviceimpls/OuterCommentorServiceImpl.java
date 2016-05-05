package srp.mongodb.serviceimpls;

import java.util.Map;

import org.bson.Document;

import srp.mongodb.main.JsonParsor;
import srp.mongodb.main.MongoProxy;
import srp.mongodb.services.OuterCommentorService;
import srp.mongodb.utils.Names;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class OuterCommentorServiceImpl implements OuterCommentorService {

	@Override
	public String insertOne(String name, String platform,
			Map<String, String> info) {
		MongoCollection<Document> collection = MongoProxy
				.getCollOuterCommentor();
		try {
			if (collection.find(Filters.and(
					Filters.eq(Names.DCOuterCommentor_name, name),
					Filters.eq(Names.DCOuterCommentor_platform, platform))) != null) {
				return JsonParsor.succeed("用户已存在");
			} else {
				collection.insertOne(new Document().append(
						Names.DCOuterCommentor_name, name).append(
						Names.DCOuterCommentor_platform, platform));
				return JsonParsor.succeed();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonParsor.fail();
	}

}
