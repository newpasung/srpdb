package srp.mongodb.serviceimpls;

import java.util.Arrays;

import org.bson.Document;
import org.bson.types.ObjectId;

import srp.mongodb.main.JsonParsor;
import srp.mongodb.main.MongoProxy;
import srp.mongodb.services.CommentService;
import srp.mongodb.utils.Names;
import srp.mongodb.utils.StatusCode;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class CommentServiceImpl implements CommentService {

	@Override
	public String insertOne(String productId, String content, String time,
			String commentor, String source_url, String[] imgurlsInComment,
			String avatar, String rate) {
		MongoCollection<Document> collection = MongoProxy.getCollComment();
		try {
			collection.insertOne(new Document()
			.append(Names.DCComment_content, content)
			.append(Names.DCComment_commentor, commentor)
			.append(Names.DCComment_productId, productId)
			.append(Names.DCComment_sourceurl, source_url)
			.append(Names.DCComment_time, time)
			.append(Names.DCComment_imgincomment, Arrays.asList(imgurlsInComment))
			.append(Names.DCComment_avatar, avatar)
					.append(Names.DCComment_rate, rate)
					);	
			return JsonParsor.succeed("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonParsor.fail(StatusCode.FAILE, "操作失败");
	}

	@Override
	public String deleteOne(String commentId) {
		MongoCollection<Document> collection = MongoProxy.getCollComment();
		try {
			collection.deleteOne(Filters.eq(Names.DCComment_commentid,
					new ObjectId(commentId)));
			return JsonParsor.succeed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonParsor.fail();
	}

	@Override
	public String getSome(String productId, int listType, int count,
			int pageIndex) {
		if (pageIndex <= 0) {
			return JsonParsor.fail(StatusCode.PARAMOUTOFRANGE, "pageIndex>=1");
		}
		if (count < 0) {
			return JsonParsor.fail(StatusCode.PARAMOUTOFRANGE, "count>=0");
		}
		MongoCollection<Document> collection = MongoProxy.getCollComment();
		return JsonParsor.succeedPrintAll("comments",
				collection.find().limit(count).skip(count * (pageIndex - 1)));
	}

}
