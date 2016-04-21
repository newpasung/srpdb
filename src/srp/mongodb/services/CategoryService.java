package srp.mongodb.services;


/*
 * 和商品的分类相关的接口
 * */
public interface CategoryService {

	/*
	 * @return 以json格式返回所有的目录，示例为 { { parent : 'IT', child : ['phone','camera'] } }
	 */
	String getAllCategory();

	/*
	 * 通过目录名字获取商品数据
	 * 
	 * @param categoryName 目录名称
	 * 
	 * @param listType 采用什么排列方式（未定义）
	 * 
	 * @param count 一页返回多少条数据
	 * 
	 * @param pageIndex 分页索引
	 * 
	 * @return 返回json格式的商品列表数据
	 */
	String getProductsByCat(String categoryName, int listType, int pageIndex,
			int count);
	
	/*
	 * 初始化商品分类数据
	 * 
	 * @param categories 通过map结构初始化商品分类数据，map的key为父分类名，value里面村子分类名
	 */
	void iniCategory(String filePath);
	

}
