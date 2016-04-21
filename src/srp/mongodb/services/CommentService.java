package srp.mongodb.services;

/*
 * 过滤之后的评论数据的操作接口
 * */
public interface CommentService {

	/*
	 * 插入一条评论数据
	 */
	void insertOne(String productId, String content, String time,
			String commentor, String source_url, String[] imgurlsInComment,
			String avatar);

	/*
	 * 根据commentId删除一条评论
	 * @param commentId 要删除的评论的id
	 */
	void deleteOne(String commentId);

	/*
	 * 获取一系列可用于展示的评论，
	 * 
	 * @param productId 获取productId所指商品的评论
	 * 
	 * @param listType 评论的排列方式（未定）
	 * 
	 * @param count 获取评论的数量
	 * 
	 * @param pageIndex 分页的索引
	 * 
	 * @return 返回json格式的评论列表
	 */
	String getSome(String productId, String listType, int count,
 int pageIndex);

}
