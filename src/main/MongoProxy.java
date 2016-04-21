package main;

import org.bson.Document;

import utils.Names;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;

/*用于测试目的*/
public class MongoProxy {
	public static MongoClient client;
	public static MongoDatabase database;

	public static boolean init() {
		try {
			client = new MongoClient("localhost", 27017);
			database = client.getDatabase("SRPQA");
			CreateCollectionOptions opts = new CreateCollectionOptions();
			if (database.getCollection(Names.CollNameAccount) == null) {
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
			return true;
		} catch (Exception e) {
			// TODO: handle exception
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
		return database;
	}

	public static MongoCollection<Document> getCollAccount() {
		return database.getCollection(Names.CollNameAccount);
	}

	public static MongoCollection<Document> getCollIniPro() {
		return database.getCollection(Names.CollNameIniProduct);
	}

	public static MongoCollection<Document> getCollIniComment() {
		return database.getCollection(Names.CollNameIniComment);
	}

	public static MongoCollection<Document> getCollCategory() {
		return database.getCollection(Names.CollNameCategory);
	}

	public static MongoCollection<Document> getCollComment() {
		return database.getCollection(Names.CollNameComment);
	}

	public static MongoCollection<Document> getCollQCRecord() {
		return database.getCollection(Names.CollNameQCRecord);
	}

	public static MongoCollection<Document> getCollProduct() {
		return database.getCollection(Names.CollNameProduct);
	}

	public static MongoCollection<Document> getCollPrice() {
		return database.getCollection(Names.CollNameProductPrice);
	}

	public static MongoCollection<Document> getCollProAnalysis() {
		return database.getCollection(Names.CollNameProductAnalysis);
	}

	public static MongoCollection<Document> getCollProMapping() {
		return database.getCollection(Names.CollNameProductMap);
	}

}
