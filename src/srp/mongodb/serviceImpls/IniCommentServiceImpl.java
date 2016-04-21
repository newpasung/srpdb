package srp.mongodb.serviceImpls;

import java.util.Arrays;

import main.JsonParsor;
import main.Logger;
import main.MongoProxy;

import org.bson.Document;
import org.bson.types.ObjectId;

import srp.mongodb.services.IniCommentService;
import utils.Names;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class IniCommentServiceImpl implements IniCommentService {

	@Override
	public void insertOne(String productId, String content, String time,
			String commentor, String source_url, String avatar, String[] imgUrls) {
		MongoCollection<Document> collection = MongoProxy.getCollIniComment();
		try {
			collection.insertOne(new Document()
					.append(Names.DCIniComment_productId, productId)
					.append(Names.DCIniComment_content, content)
					.append(Names.DCIniComment_time, time)
					.append(Names.DCIniComment_commentor, commentor)
					.append(Names.DCIniComment_source, source_url)
					.append(Names.DCIniComment_avatar, avatar)
					.append(Names.DCIniComment_imgincomment,
							Arrays.asList(imgUrls))
					.append(Names.DCIniComment_dirtyFilter, 0)
					.append(Names.DCIniComment_dirtyEmoAnalysis, 0));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.debug("œµÕ≥¥ÌŒÛ£¨≤Â»Î∆¿¬€ ß∞‹");
		}

	}

	@Override
	public void deleteOne(String commentId) {
		MongoCollection<Document> collection = MongoProxy.getCollIniComment();
		try {
			collection.deleteOne(Filters.eq(Names.DCIniComment_commentid,
					new ObjectId(commentId)));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.debug("œµÕ≥¥ÌŒÛ£¨…æ≥˝ ß∞‹");
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
		MongoCollection<Document> collection = MongoProxy.getCollIniComment();
		return JsonParsor.succeedPrintAll("comments",
				collection
				.find(Filters.eq("_id.$oid", productId)).limit(count)
				.skip(count * (pageIndex - 1)));
	}

	@Override
	public void markFiltered(String inicommentId, int dirty) {
		MongoCollection<Document> collection = MongoProxy.getCollIniComment();
		try {
			collection.updateOne(Filters.eq(Names.DCIniComment_commentid,new ObjectId(inicommentId))
					, new Document("set",new Document(Names.DCIniComment_dirtyFilter, dirty)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void markEmotionAnalysed(String inicommentId, int dirty) {
		MongoCollection<Document> collection = MongoProxy.getCollIniComment();
		try {
			collection.updateOne(Filters.eq(Names.DCIniComment_dirtyEmoAnalysis,
					new ObjectId(inicommentId)), new Document("set",
					new Document(Names.DCIniComment_dirtyEmoAnalysis, dirty)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
