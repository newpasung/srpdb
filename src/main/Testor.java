package main;

import srp.mongodb.serviceImpls.QCRecordServiceImpl;
import srp.mongodb.services.QCRecordService;

/*用于测试目的*/
public class Testor {

	public static void main(String[] args) {
		MongoProxy.init();
		/*UserService service = new UserServiceImpl();
		Logger.debug("用户注册" + service.register("ganzicheng", "111111"));
		Logger.debug("用户登录" + service.login("ganzicheng", "111111"));
		Logger.debug("修改密码为222222"
				+ service.alterPasswd("ganzicheng", "111111", "222222"));
		Logger.debug("重新登录" + service.login("ganzicheng", "222222"));
		IniProductService iniProductService=new IniProductServiceImpl();
		Map<String, String> attrs = new HashMap<String, String>();
		attrs.put("属性1", "属性值1");
		attrs.put("属性2", "属性值2");
		iniProductService.insertOne("名称2", "分类1", "品牌1",
				new String[] { "imageurl1" }, attrs, "http://soururl.com",
				new String[] { "tag1", "tag2" });
		iniProductService.insertOne("名称3", "分类1", "品牌1",
				new String[] { "imageurl1" }, attrs, "http://soururl.com",
				new String[] { "tag1", "tag2" });
		iniProductService.insertOne("名称4", "分类1", "品牌1",
				new String[] { "imageurl1" }, attrs, "http://soururl.com",
				new String[] { "tag1", "tag2" });
		Logger.debug("插入数据后展示十条：" + iniProductService.getSomeUnhandled());*/
		/*IniCommentService iniCommentService = new IniCommentServiceImpl();
		iniCommentService.insertOne("2", "content", "time", "commentor",
				"source", "avatar", new String[] { "url1", "url2" });
		Logger.debug("插入数据后读取" + iniCommentService.getSome(null, null, 10, 1));*/
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
		Logger.debug("查找数据"
				+ qcRecordService.getSomeByIds("5718bee283d2f117b438585c"));
		MongoProxy.shutDown();
	}

}
