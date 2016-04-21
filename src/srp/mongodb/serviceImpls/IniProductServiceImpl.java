package srp.mongodb.serviceImpls;

import java.util.Arrays;
import java.util.Map;

import main.MongoProxy;

import org.bson.Document;

import srp.mongodb.services.IniProductService;
import utils.IniProductHelper;
import utils.Names;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class IniProductServiceImpl implements IniProductService {

	@Override
	public void insertOne(String name, String category, String brand,
			String[] imgUrls,
			Map<String, String> attrs, String sourceUrl, String[] tags) {
		MongoCollection<Document> collection = MongoProxy.getCollIniPro();
		try {
			collection.insertOne(new Document()
					.append(Names.DCIniPro_name, name)
					.append(Names.DCIniPro_brand, brand)
					.append(Names.DCIniPro_inicategory, category)
					.append(Names.DCIniPro_imgurls, Arrays.asList(imgUrls))
					.append(Names.DCIniPro_attributes, attrs)
					.append(Names.DCIniPro_sourceurl, sourceUrl)
					.append(Names.DCIniPro_tags, Arrays.asList(tags))
					.append(Names.DCIniPro_dirty, 0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getSomeUnhandled() {
		MongoCollection<Document> collection = MongoProxy.getCollIniPro();
		return IniProductHelper.printAll(collection.find(
				Filters.eq(Names.DCIniPro_dirty, 0)).limit(10));
	}

	@Override
	public String mapping(String idBefore, String idAfter) {
		MongoCollection<Document> collection = MongoProxy.getCollProMapping();
		try {
			collection.insertOne(new Document().append(Names.DCProMap_beforeid,
					idBefore).append(Names.DCProMap_afterid, idAfter));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
