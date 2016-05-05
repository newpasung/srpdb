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
	String getProductsByCat(String categoryName, String listType,
			String pageIndex, String count);
	
	/*
	 * 初始化分类的信息，注意这里是利用文件来初始化商品分类的
	 * 
	 * @param filePath 文件的路径，以“包名/文件名”为格式，不保证平台兼容性
	 */
	String iniCategory(String filePath);
	

}
