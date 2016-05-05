package srp.mongodb.serviceimpls;

import java.util.Arrays;

import org.bson.Document;
import org.bson.types.ObjectId;

import srp.mongodb.main.JsonParsor;
import srp.mongodb.main.Logger;
import srp.mongodb.main.MongoProxy;
import srp.mongodb.services.IniCommentService;
import srp.mongodb.utils.Names;
import srp.mongodb.utils.StatusCode;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class IniCommentServiceImpl implements IniCommentService {

	@Override
	public String insertOne(String productId, String content, String time,
			String commentor, String source_url, String avatar,
			String[] imgUrls, String rate) {
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
					.append(Names.DCIniComment_dirtyEmoAnalysis, 0)
					.append(Names.DCIniComment_rate, rate));
			return JsonParsor.succeed("成功插入一条评论");
		} catch (Exception e) {
			e.printStackTrace();
			Logger.debug("系统错误，插入评论失败");
		}
		return JsonParsor.fail(StatusCode.ERROR_FROM_DATABASE, "操作失败");
	}

	@Override
	public String deleteOne(String commentId) {
		MongoCollection<Document> collection = MongoProxy.getCollIniComment();
		try {
			collection.deleteOne(Filters.eq(Names.DCIniComment_commentid,
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
		MongoCollection<Document> collection = MongoProxy.getCollIniComment();
		return JsonParsor.succeedPrintAll("comments",
				collection.find(Filters.eq(Names.DCIniComment_productId, productId))
						.limit(count)
				.skip(count * (pageIndex - 1)));
	}

	@Override
	public String markFiltered(String inicommentId, int dirty) {
		MongoCollection<Document> collection = MongoProxy.getCollIniComment();
		try {
			collection.updateOne(Filters.eq(
					Names.DCIniComment_commentid, new ObjectId(inicommentId)), new Document("$set",
					new Document(Names.DCIniComment_dirtyFilter, dirty)));
			return JsonParsor.succeed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonParsor.fail();
	}

	@Override
	public String markEmotionAnalysed(String inicommentId, int dirty) {
		MongoCollection<Document> collection = MongoProxy.getCollIniComment();
		try {
			collection.updateOne(Filters.eq(Names.DCIniComment_commentid,
					new ObjectId(inicommentId)), new Document("$set",
					new Document(Names.DCIniComment_dirtyEmoAnalysis, dirty)));
			return JsonParsor.succeed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonParsor.fail();
	}

}
