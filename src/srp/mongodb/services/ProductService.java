package srp.mongodb.services;

import java.util.Map;

/*
 * 对归一化后的商品数据的操作，这里操作的数据都是直接用于显示的
 */
public interface ProductService {

	/*
	 * 获取一个商品的详细信息
	 * 
	 * @param productId 商品的productId
	 * 
	 * @return 返回json格式的商品详细信息,返回的数据内容根据实际需求定
	 */
	String getOne(String productId);
	
	
	/*
	 * 插入一条商品的数据，因为这里的数据需保持独立性，不能有重复数据，所以重复数据会失败
	 * 
	 * @param name 商品的名称
	 * 
	 * @param category 商品的分类
	 * 
	 * @param imgUrls 商品图片的地址
	 * 
	 * @param attrs 商品的属性对，如key为颜色，value为红色
	 */
	void insertOne(String name, String brand, String category,
			String[] imgUrls,
			Map<String, String> attrs);

	/*
	 * 获取商品的推荐价格信息
	 * 
	 * @param productId 商品id
	 */
	String getPrices(String productId);

	/*
	 * 更新一个商品的价格数据
	 * 
	 * @param prouductId 商品的id
	 * 
	 * @param price 商品的价格
	 * 
	 * @param platform 商品价格对应的平台
	 * 
	 * @param priceUrl 价格来源的网址
	 * 
	 * @param lastUpdatedTime 这个数据更新时间，即调用此函数时的时间
	 */
	void updatePrice(String productId, double price, String platform,
			String priceUrl, String lastUpdatedTime);

	/*
	 * 更改一个商品的一项规格属性
	 * 
	 * @param productId 商品的id
	 * 
	 * @param attrName 旧的属性名称
	 * 
	 * @param newValue 新的属性值
	 */
	void alterAttr(String productId, String attrName, String newValue);

	/*
	 * 删除某商品的一条属性
	 * 
	 * @param 产品的id
	 * 
	 * @param attrName 属性的名称，如颜色
	 * 
	 * @return undefined
	 */
	String removeAttr(String productId, String attrName);

	/*
	 * @param tagsAndrate 格式为 外观：10 表示关键词及其分数
	 */
	String setTagsandRate(String productId, Map<String, String> tagsAndrate);

	/*
	 * 获取一个商品的关键词，即评论热词
	 * 
	 * @param productId 商品id
	 * 
	 * @return 返回json格式的一组关键词
	 */
	String getTags(String productId);

}
