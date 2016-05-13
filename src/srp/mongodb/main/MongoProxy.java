package srp.mongodb.main;

import org.bson.Document;

import srp.mongodb.serviceimpls.CategoryServiceImpl;
import srp.mongodb.services.CategoryService;
import srp.mongodb.utils.Names;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/*用于测试目的*/
public class MongoProxy {
	public static MongoClient client;
	public static MongoDatabase database;

	public synchronized static boolean init() {
		try {
			if (client == null) {
				client = new MongoClient("localhost", 27017);
				database = client.getDatabase("SRPQA");
				if (database
						.getCollection(srp.mongodb.utils.Names.CollNameAccount) == null) {
					database.createCollection(Names.CollNameAccount);
				}
				if (database.getCollection(Names.CollNameIniProduct) == null) {
					database.createCollection(Names.CollNameIniProduct);
				}
				if (database.getCollection(Names.CollNameIniComment) == null) {
					database.createCollection(Names.CollNameIniComment);
				}
				if (database.getCollection(Names.CollNameCategory) == null) {
					database.createCollection(Names.CollNameCategory);
					CategoryService categoryService = new CategoryServiceImpl();
					categoryService.iniCategory("/srp/mongodb/serviceimpls/categories.txt");
				}
				if (database.getCollection(Names.CollNameComment) == null) {
					database.createCollection(Names.CollNameComment);
				}
				if (database.getCollection(Names.CollNameQCRecord) == null) {
					database.createCollection(Names.CollNameQCRecord);
				}
				if (database.getCollection(Names.CollNameProduct) == null) {
					database.createCollection(Names.CollNameProduct);
				}
				if (database.getCollection(Names.CollNameProductPrice) == null) {
					database.createCollection(Names.CollNameProductPrice);
				}
				if (database.getCollection(Names.CollNameProductAnalysis) == null) {
					database.createCollection(Names.CollNameProductAnalysis);
				}
				if (database.getCollection(Names.CollNameProductMap) == null) {
					database.createCollection(Names.CollNameProductMap);
				}
				if (database.getCollection(Names.CollNameOuterCommentor) == null) {
					database.createCollection(Names.CollNameOuterCommentor);
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static void shutDown() {
		if (client != null) {
			Logger.debug("数据库关闭：" + database.getName());
			client.close();
			Logger.debug("--数据库客户端关闭--");
		}
	}

	public static MongoDatabase getDatabase() {
		if (database == null) {
			init();
			return database;
		} else {
			return database;
		}

	}

	public static MongoCollection<Document> getCollAccount() {
		return getDatabase().getCollection(Names.CollNameAccount);
	}

	public static MongoCollection<Document> getCollIniPro() {
		return getDatabase().getCollection(Names.CollNameIniProduct);
	}

	public static MongoCollection<Document> getCollIniComment() {
		return getDatabase().getCollection(Names.CollNameIniComment);
	}

	public static MongoCollection<Document> getCollCategory() {
		return getDatabase().getCollection(Names.CollNameCategory);
	}

	public static MongoCollection<Document> getCollComment() {
		return getDatabase().getCollection(Names.CollNameComment);
	}

	public static MongoCollection<Document> getCollQCRecord() {
		return getDatabase().getCollection(Names.CollNameQCRecord);
	}

	public static MongoCollection<Document> getCollProduct() {
		return getDatabase().getCollection(Names.CollNameProduct);
	}

	public static MongoCollection<Document> getCollPrice() {
		return getDatabase().getCollection(Names.CollNameProductPrice);
	}

	public static MongoCollection<Document> getCollProAnalysis() {
		return getDatabase().getCollection(Names.CollNameProductAnalysis);
	}

	public static MongoCollection<Document> getCollProMapping() {
		return getDatabase().getCollection(Names.CollNameProductMap);
	}

	public static MongoCollection<Document> getCollOuterCommentor() {
		return getDatabase().getCollection(Names.CollNameOuterCommentor);
	}

}
