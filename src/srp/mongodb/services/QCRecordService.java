package srp.mongodb.services;

/*
 * 质量抽检记录数据的操作接口
 * */
public interface QCRecordService {

	/*
	 * 插入一条质检数据
	 * 
	 * @param qcItem 质检的项目
	 * 
	 * @param qcDate 质检的日期，格式为年-月-日
	 * 
	 * @param productName 质检的商品的名称
	 * 
	 * @param brand 产品的品牌
	 * 
	 * @param isFined 质检是否通过
	 * 
	 * @param qcResult 质检的结果，这个包含文字的描述
	 * 
	 * @param qcInstitude 质检机构
	 */
	void insertOne(String qcItem, String productName,
			String brand, int isFined, String qcResult, String qcInstitude);

	/*
	 * 通过 qc的id获取质检记录的详细信息
	 * 
	 * @qcIds 想要得到的一组质检报告的id
	 */
	String getSomeByIds(String... qcIds);

}
