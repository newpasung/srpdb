package srp.mongodb.serviceimpls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.types.ObjectId;

import srp.mongodb.main.JsonParsor;
import srp.mongodb.main.MongoProxy;
import srp.mongodb.services.QCRecordService;
import srp.mongodb.utils.Names;
import srp.mongodb.utils.StatusCode;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public class QCRecordServiceImpl implements QCRecordService {

	@Override
	public String insertOne(String qcItem, String productName,
			String brand, int isFined, String qcResult, String qcInstitude,Map<String, String> attrs) {
		MongoCollection<Document> collection = MongoProxy.getCollQCRecord();
		try {
			Document document =new Document();
			document.putAll(attrs);
			collection.insertOne(document
					.append(Names.DCQCRecord_productname, productName)
					.append(Names.DCQCRecord_item, qcItem)
					.append(Names.DCQCRecord_brand, brand)
					.append(Names.DCQCRecord_isFine, isFined)
					.append(Names.DCQCRecord_resulttext, qcResult)
					.append(Names.DCQCRecord_institude, qcInstitude))
					;
			return JsonParsor.succeed("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonParsor.fail(StatusCode.ERROR_FROM_DATABASE, "操作失败");
	}

	@Override
	public String getSomeByIds(String... qcIds) {
		if (qcIds == null) {
			return JsonParsor.fail(StatusCode.INVALIDDATA, "字符为空");
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
