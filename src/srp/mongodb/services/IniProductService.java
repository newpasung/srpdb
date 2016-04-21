package srp.mongodb.services;

import java.util.Map;

/*
 * @author season
 * 提供商品原始数据操作的接口
 * */
public interface IniProductService {

	/*
	 * 插入一条商品原始数据，因为这里的数据需保持独立性，不能有重复数据，所以重复数据会失败
	 * 
	 * @param name 商品的名称
	 * 
	 * @param category 商品的分类
	 * 
	 * @param imgUrls 商品图片的地址
	 * 
	 * @param brand 商品的品牌
	 * 
	 * @param attrs 商品的属性对，如key为颜色，value为红色
	 * 
	 * @param sourceUrl 商品信息来源网址
	 * 
	 * @param tags 电商平台提供的商品评价关键词
	 */
	void insertOne(String name, String category, String brand,
			String[] imgUrls,
			Map<String, String> attrs, String sourceUrl, String[] tags);

	/*
	 * 获取未经处理的商品数据
	 * 
	 * @return 返回未处理的商品数据所有数据
	 */
	String getSomeUnhandled();

	/*
	 * 归一化后的商品映射关系设置
	 * 
	 * @param idBefore 及原始数据的id
	 * 
	 * @param idAfter 及对应到唯一的归一化后的商品的id
	 * 
	 * @return undefined
	 */
	String mapping(String idBefore, String idAfter);

}
