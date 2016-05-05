package srp.mongodb.services;

import java.util.Map;

public interface OuterCommentorService {
	
	
	/*
	 * 插入一个评论人数据，假如已经存在的话不会重复插入
	 * 
	 * @param name 用户名
	 * 
	 * @param platform 来自于哪个平台
	 * 
	 * @param info 评论者信息的key-value
	 * 
	 * @return 评论人的信息
	 */
	String insertOne(String name, String platform, Map<String, String> info);
	
}
