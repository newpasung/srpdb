package srp.mongodb.services;

/*
 * 过滤之后的评论数据的操作接口
 * */
public interface CommentService {

	public static int LISTTYPE_DEFAULT = 0;// 默认的排列方式
	public static int ITEMCOUNT_DEFAULT = 10;// 一次请求默认返回十条数据
	public static int PAGEINDEX_DEFAULT = 1;// 默认返回第一页评论
	
	/*
	 * 插入一条评论数据
	 * 
	 * @param productId 商品id
	 * 
	 * @param content 评论内容
	 * 
	 * @param time 评论的时间
	 * 
	 * @param commentor 评论人
	 * 
	 * @param source_url 评论来源网址
	 * 
	 * @param imgurlsInComment 评论里面的图片地址
	 * 
	 * @param avatar 评论人头像
	 * 
	 * @param rate 这条评论的评分，范围是1-10
	 */
	String insertOne(String productId, String content, String time,
			String commentor, String source_url, String[] imgurlsInComment,
			String avatar, String rate);

	/*
	 * 根据commentId删除一条评论
	 * @param commentId 要删除的评论的id
	 */
	String deleteOne(String commentId);

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
	String getSome(String productId, int listType, int count,
 int pageIndex);

}
