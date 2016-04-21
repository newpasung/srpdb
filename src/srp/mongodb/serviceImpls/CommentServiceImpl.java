package srp.mongodb.serviceImpls;

import java.util.Arrays;

import main.JsonParsor;
import main.MongoProxy;

import org.bson.Document;
import org.bson.types.ObjectId;

import srp.mongodb.services.CommentService;
import utils.Names;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class CommentServiceImpl implements CommentService {

	@Override
	public void insertOne(String productId, String content, String time,
			String commentor, String source_url, String[] imgurlsInComment,
			String avatar) {
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
					);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOne(String commentId) {
		MongoCollection<Document> collection = MongoProxy.getCollComment();
		try {
			collection.deleteOne(Filters.eq(Names.DCComment_commentid,
					new ObjectId(commentId)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getSome(String productId, String listType, int count,
			int pageIndex) {
		if (pageIndex <= 0) {
			return JsonParsor.fail("pageIndex>=1");
		}
		if (count < 0) {
			return JsonParsor.fail("count>=0");
		}
		MongoCollection<Document> collection = MongoProxy.getCollComment();
		return JsonParsor.succeedPrintAll("comments",
				collection.find().limit(count).skip(count * (pageIndex - 1)));
	}

}
