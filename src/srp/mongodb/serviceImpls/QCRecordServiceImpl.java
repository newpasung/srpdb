package srp.mongodb.serviceImpls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.JsonParsor;
import main.MongoProxy;

import org.bson.Document;
import org.bson.types.ObjectId;

import srp.mongodb.services.QCRecordService;
import utils.Names;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public class QCRecordServiceImpl implements QCRecordService {

	@Override
	public void insertOne(String qcItem, String productName,
			String brand, int isFined, String qcResult, String qcInstitude) {
		MongoCollection<Document> collection = MongoProxy.getCollQCRecord();
		try {
			collection.insertOne(new Document()
					.append(Names.DCQCRecord_productname, productName)
					.append(Names.DCQCRecord_item, qcItem)
					.append(Names.DCQCRecord_brand, brand)
					.append(Names.DCQCRecord_isFine, isFined)
					.append(Names.DCQCRecord_resulttext, qcResult)
					.append(Names.DCQCRecord_institude, qcInstitude));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public String getSomeByIds(String... qcIds) {
		if (qcIds == null) {
			return JsonParsor.fail("×Ö·ûÎª¿Õ");
		}
		List<ObjectId> objectIds = new ArrayList<>();
		for (int i = 0; i < qcIds.length; i++) {
			objectIds.add(new ObjectId(qcIds[i]));
		}
		MongoCollection<Document> collection = MongoProxy.getCollQCRecord();
		FindIterable<Document> findIterable = collection.find(
				new Document().append(Names.DCQCRecord_id, new Document("$in",
						objectIds)));
		return JsonParsor.succeedPrintAll("qcrecords", findIterable);
	}


}
