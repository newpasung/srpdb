package main;

import srp.mongodb.serviceImpls.QCRecordServiceImpl;
import srp.mongodb.services.QCRecordService;

/*���ڲ���Ŀ��*/
public class Testor {

	public static void main(String[] args) {
		MongoProxy.init();
		/*UserService service = new UserServiceImpl();
		Logger.debug("�û�ע��" + service.register("ganzicheng", "111111"));
		Logger.debug("�û���¼" + service.login("ganzicheng", "111111"));
		Logger.debug("�޸�����Ϊ222222"
				+ service.alterPasswd("ganzicheng", "111111", "222222"));
		Logger.debug("���µ�¼" + service.login("ganzicheng", "222222"));
		IniProductService iniProductService=new IniProductServiceImpl();
		Map<String, String> attrs = new HashMap<String, String>();
		attrs.put("����1", "����ֵ1");
		attrs.put("����2", "����ֵ2");
		iniProductService.insertOne("����2", "����1", "Ʒ��1",
				new String[] { "imageurl1" }, attrs, "http://soururl.com",
				new String[] { "tag1", "tag2" });
		iniProductService.insertOne("����3", "����1", "Ʒ��1",
				new String[] { "imageurl1" }, attrs, "http://soururl.com",
				new String[] { "tag1", "tag2" });
		iniProductService.insertOne("����4", "����1", "Ʒ��1",
				new String[] { "imageurl1" }, attrs, "http://soururl.com",
				new String[] { "tag1", "tag2" });
		Logger.debug("�������ݺ�չʾʮ����" + iniProductService.getSomeUnhandled());*/
		/*IniCommentService iniCommentService = new IniCommentServiceImpl();
		iniCommentService.insertOne("2", "content", "time", "commentor",
				"source", "avatar", new String[] { "url1", "url2" });
		Logger.debug("�������ݺ��ȡ" + iniCommentService.getSome(null, null, 10, 1));*/
		/*
		 * CategoryService categoryService = new CategoryServiceImpl();
		 * categoryService
		 * .iniCategory("src/srp/mongodb/serviceImpls/categories.txt");
		 */
		QCRecordService qcRecordService = new QCRecordServiceImpl();
		/*
		 * qcRecordService.insertOne("items", "productname", "brand", 1,
		 * "no problem", "institude");
		 */
		Logger.debug("��������"
				+ qcRecordService.getSomeByIds("5718bee283d2f117b438585c"));
		MongoProxy.shutDown();
	}

}
