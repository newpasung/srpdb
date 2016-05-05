package srp.mongodb.services;


/*
 * ����Ʒ�ķ�����صĽӿ�
 * */
public interface CategoryService {

	/*
	 * @return ��json��ʽ�������е�Ŀ¼��ʾ��Ϊ { { parent : 'IT', child : ['phone','camera'] } }
	 */
	String getAllCategory();

	/*
	 * ͨ��Ŀ¼���ֻ�ȡ��Ʒ����
	 * 
	 * @param categoryName Ŀ¼����
	 * 
	 * @param listType ����ʲô���з�ʽ��δ���壩
	 * 
	 * @param count һҳ���ض���������
	 * 
	 * @param pageIndex ��ҳ����
	 * 
	 * @return ����json��ʽ����Ʒ�б�����
	 */
	String getProductsByCat(String categoryName, String listType,
			String pageIndex, String count);
	
	/*
	 * ��ʼ����Ʒ��������
	 * 
	 * @param categories ͨ��map�ṹ��ʼ����Ʒ�������ݣ�map��keyΪ����������value������ӷ�����
	 */
	String iniCategory(String filePath);
	

}
