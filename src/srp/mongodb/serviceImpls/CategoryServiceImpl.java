package srp.mongodb.serviceimpls;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import main.JsonParsor;
import main.Logger;
import main.MongoProxy;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.bson.Document;

import srp.mongodb.services.CategoryService;
import srp.mongodb.services.ProductService;
import utils.Names;
import utils.StatusCode;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class CategoryServiceImpl implements CategoryService {

	@Override
	public String getAllCategory() {
		MongoCollection<Document> collection = MongoProxy.getCollCategory();
		return JsonParsor.succeedPrintAll("categories", collection.find());
	}

	@Override
	public String getProductsByCat(String categoryName, String listType,
			String pageIndex, String count) {
		int pageIndex_int;
		int count_int;
		try {
			pageIndex_int = Integer.parseInt(pageIndex);
			if (pageIndex_int<=1) {
				pageIndex_int = 1;
			}
		} catch (Exception e) {
			pageIndex_int = 1;
		}
		try {
			count_int = Integer.parseInt(count);
			if (count_int<0) {
				count_int = ProductService.ITEMCOUNT_DEFAULT;
			}
		} catch (Exception e) {
			count_int = ProductService.ITEMCOUNT_DEFAULT;
		}
		MongoCollection<Document> collection = MongoProxy.getCollProduct();
		FindIterable<Document> findIterable = collection.find(Filters.eq(
				Names.DCProduct_category, categoryName)).skip(
				(pageIndex_int <= 1 ? 0 :( pageIndex_int - 1)*count_int))
				.limit(pageIndex_int);
		return JsonParsor.succeedPrintAll("products", findIterable);
	}

	@Override
	public String iniCategory(String filePath) {
		try {
			String iniStrData = "";
			InputStream inputStream = getClass().getResourceAsStream(filePath);
			InputStreamReader reader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				iniStrData += line;
			}
			Logger.debug(iniStrData);
			MongoCollection<Document> collection = MongoProxy.getCollCategory();
			JSONArray jsonArray = JSONArray.fromObject(iniStrData);
			List<Document> documents = new ArrayList<>();
			for (int i = 0; i < jsonArray.size(); i++) {
				documents.add(Document.parse(((JSONObject) jsonArray.get(i))
						.toString()));
			}
			collection.insertMany(documents);
			return JsonParsor.succeed("成功");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return JsonParsor.fail(StatusCode.INVALIDDATA, "路径错误");
		} catch (IOException e) {
			e.printStackTrace();
			return JsonParsor.fail(StatusCode.FAILE, "文件读写错误");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonParsor.fail(StatusCode.FAILE, "未知错误，注意不能多次初始化");
		}
	}

}
