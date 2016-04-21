package srp.mongodb.serviceImpls;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.JsonParsor;
import main.Logger;
import main.MongoProxy;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.bson.Document;

import srp.mongodb.services.CategoryService;
import utils.Names;

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
	public String getProductsByCat(String categoryName, int listType,
			int pageIndex, int count) {
		MongoCollection<Document> collection = MongoProxy.getCollProduct();
		FindIterable<Document> findIterable = collection.find(Filters.eq(
				Names.DCProduct_category, categoryName));
		return JsonParsor.succeedPrintAll("products", findIterable);
	}

	@Override
	public void iniCategory(String filePath) {
		try {
			String iniStrData = "";
			File file = new File(filePath);
			FileReader reader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				iniStrData += line;
			}
			MongoCollection<Document> collection = MongoProxy.getCollCategory();
			JSONArray jsonArray = JSONArray.fromObject(iniStrData);
			List<Document> documents = new ArrayList<>();
			for (int i = 0; i < jsonArray.size(); i++) {
				documents.add(Document.parse(((JSONObject) jsonArray.get(i))
						.toString()));
			}
			collection.insertMany(documents);
			Logger.debug("类别初始化成功"
					+ JsonParsor.succeedPrintAll("categories",
							collection.find()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
