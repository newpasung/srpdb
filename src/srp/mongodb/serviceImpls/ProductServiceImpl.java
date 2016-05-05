package srp.mongodb.serviceimpls;

import java.util.Arrays;
import java.util.Map;

import org.bson.Document;
import org.bson.types.ObjectId;

import srp.mongodb.main.JsonParsor;
import srp.mongodb.main.MongoProxy;
import srp.mongodb.services.ProductService;
import srp.mongodb.utils.Names;
import srp.mongodb.utils.StatusCode;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class ProductServiceImpl implements ProductService {

	@Override
	public String getOne(String productId) {
		MongoCollection<Document> collection = MongoProxy.getCollProduct();
		FindIterable<Document> findIterable = collection.find(Filters.eq(
				Names.DCProduct_productId, new ObjectId(productId)));
		return JsonParsor.succeedPrintAll("product", findIterable);
	}

	@Override
	public String insertOne(String name, String brand, String category,
			String[] imgUrls,
			Map<String, String> attrs) {
		MongoCollection<Document> collection = MongoProxy.getCollProduct();
		try {
			collection.insertOne(new Document()
					.append(Names.DCProduct_name, name)
					.append(Names.DCProduct_category, category)
					.append(Names.DCProduct_imgurls, Arrays.asList(imgUrls))
					.append(Names.DCProduct_attributes, attrs)
					.append(Names.DCProduct_brand, brand)
					.append(Names.DCProduct_favourcount, 0));
			return JsonParsor.succeed("成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonParsor.fail(StatusCode.FAILE, "操作失败");
	}

	@Override
	public String getPrices(String productId) {
		MongoCollection<Document> collection = MongoProxy.getCollPrice();
		FindIterable<Document> findIterable = collection.find(Filters.eq(
				Names.DCProduct_productId, productId));
		return JsonParsor.succeedPrintAll("prices", findIterable);
	}

	@Override
	public String updatePrice(String productId, double price, String platform,
			String priceUrl, String lastUpdatedTime) {
		MongoCollection<Document> collection = MongoProxy.getCollPrice();
		try {
			collection.insertOne(new Document()
					.append(Names.DCPrice_productid, productId)
					.append(Names.DCPrice_price, price)
					.append(Names.DCPrice_platform, platform)
					.append(Names.DCPrice_sourceUrl, priceUrl)
					.append(Names.DCPrice_lastupdatedtime, lastUpdatedTime));
			return JsonParsor.succeed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonParsor.fail();
	}

	@Override
	public String alterAttr(String productId, String attrName, String newValue) {
		MongoCollection<Document> collection = MongoProxy.getCollProduct();
		Document document = collection.find(
				Filters.eq(Names.DCProduct_productId, new ObjectId(productId)))
				.first();
		if (document == null) {
			return JsonParsor.fail();
		}
		try {
			collection.updateOne(
					Filters.eq(Names.DCProduct_productId, new ObjectId(
							productId)),
					new Document("$set", new Document().append(
							Names.DCProduct_attributes + "." + attrName,
							newValue)));
			return JsonParsor.succeed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonParsor.fail();
	}

	@Override
	public String removeAttr(String productId, String attrName) {
		MongoCollection<Document> collection = MongoProxy.getCollProduct();
		try {
			collection.updateOne(Filters.eq(Names.DCProduct_productId, new ObjectId(productId))
					, new Document("$unset"
					,new Document(Names.DCProduct_attributes+"."+attrName,"")));
			return JsonParsor.succeed("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonParsor.fail();

	}

	@Override
	public String getTags(String productId) {
		MongoCollection<Document> collection = MongoProxy.getCollProAnalysis();
		Document data = collection.find(Filters.eq(
				Names.DCProAnalysis_productId, productId)).first();
		if (data == null) {
			return JsonParsor.fail(StatusCode.INVALIDDATA, "数据不存在");
		}
		return JsonParsor.succeedPrintAll("analysis", data);
	}

	@Override
	public String setTagsandRate(String productId,
			Map<String, String> tagsAndrate) {
		MongoCollection<Document> collection = MongoProxy.getCollProAnalysis();
		try {
			if (collection.find(Filters.eq(Names.DCProAnalysis_productId,
					productId)).first() == null) {
				collection.insertOne(new Document()
				.append(Names.DCProAnalysis_productId, productId)
				.append(Names.DCProAnalysis_Keyrates,tagsAndrate));
			}else{
				collection.updateOne(Filters.eq(Names.DCProAnalysis_productId,
						productId), new Document("$set",
						new Document(Names.DCProAnalysis_Keyrates, tagsAndrate)));
			}
			return JsonParsor.succeed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonParsor.fail();
	}

	@Override
	public String getSome(int count, int page) {
		MongoCollection<Document> collection = MongoProxy.getCollProduct();
		if (page < 1) {
			return JsonParsor.fail(StatusCode.INVALIDPARAMETER, "page<1");
		}
		try {
			return JsonParsor.succeedPrintAll("products", collection.find()
					.limit(count).skip(page > 1 ? (page - 1) * count : 0));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonParsor.fail();
	}

}
