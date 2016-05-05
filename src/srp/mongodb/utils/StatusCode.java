package srp.mongodb.utils;

public class StatusCode {
	public static int SUCCESS = 200;// 正常
	public static int INVALIDACCPASWD = 400;// 不合理的账号或密码
	public static int NEEDPARAM = 401;// 缺少参数
	public static int INVALIDDATA = 402;// 内部数据缺失或错误
	public static int PARAMOUTOFRANGE = 403;// 参数不在范围内
	public static int DUPLICATE_ACCOUNT = 405;// 账号重复
	public static int ERROR_FROM_DATABASE = 406;// 数据库异常
	public static int INVALIDPARAMETER = 407;// 提供的参数错误
	public static int OPERATION_STATUS_CONFLICT = 408;
	public static int FAILE = 444;
}
