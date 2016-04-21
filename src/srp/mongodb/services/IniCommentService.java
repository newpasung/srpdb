package srp.mongodb.services;

/*
 * @author season
 * 提供原始评论数据操作的接口
 * */
public interface IniCommentService {

	/*
	 * 插入一条评论数据
	 */
	void insertOne(String productId, String content, String time,
			String commentor, String source_url, String avatar,
			String[] imageUrls);

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
	 * @param pageIndex 分页索引
	 * 
	 * @return 返回json格式的评论列表
	 */
	String getSome(String productId, String listType, int count,
 int pageIndex);

	
	/*
	 * 用来更改一个数据的标志位，表示是否经过过滤处理
	 * 
	 * @param dirty 0表示未过滤，1表示过滤了，默认为0
	 * 
	 * @param inicommentId 原始评论Id
	 */
	void markFiltered(String inicommentId, int dirty);

	/*
	 * 用来更改一个数据的标志位，表示是否经过情感分析
	 * 
	 * @param dirty 0表示未处理，1表示已经分析过了，默认为0
	 * 
	 * @param inicommentId 原始评论Id
	 */
	void markEmotionAnalysed(String inicommentId, int dirty);

}
