package utils;

public class Names {

	public static String CollNameAccount = "account";
	public static String DCAccount_account = "_id";
	public static String DCAccount_password = "password";
	public static String DCAccount_created_time = "createdtime";
	public static String DCAccount_emailaddress = "emailaddress";
	public static String DCAccount_avatar = "avatarurl";
	public static String DCAccount_nickname = "nickname";

	public static String CollNameIniProduct = "initialproduct";
	public static String DCIniPro_productId = "_id";
	public static String DCIniPro_name = "name";
	public static String DCIniPro_brand = "brand";
	public static String DCIniPro_inicategory = "inicategory";
	public static String DCIniPro_imgurls = "onlineimgurls";
	public static String DCIniPro_sourceurl = "sourceurl";
	public static String DCIniPro_tags = "onlinetags";
	public static String DCIniPro_prices = "prices";
	public static String DCIniPro_attributes = "attributes";
	public static String DCIniPro_dirty = "dirty";

	public static String CollNameIniComment = "inicomment";
	public static String DCIniComment_commentid = "_id";
	public static String DCIniComment_content = "content";
	public static String DCIniComment_time = "content";
	public static String DCIniComment_commentor = "commentor";
	public static String DCIniComment_source = "soucre";
	public static String DCIniComment_imgincomment = "imgsincomment";
	public static String DCIniComment_avatar = "avatar";
	public static String DCIniComment_productId = "productId";
	public static String DCIniComment_dirtyFilter = "dirtyfilter";
	public static String DCIniComment_dirtyEmoAnalysis = "dirtyemoanalysis";

	public static String CollNameCategory = "category";
	public static String DCCategory_name = "_id";
	public static String DCCategory_parent = "parentName";

	public static String CollNameComment = "comment";
	public static String DCComment_commentid = "_id";
	public static String DCComment_content = "content";
	public static String DCComment_time = "time";
	public static String DCComment_commentor = "commentor";
	public static String DCComment_sourceurl = "sourceurl";
	public static String DCComment_productId = "productId";
	public static String DCComment_imgincomment = "imgsincomment";
	public static String DCComment_avatar = "avatar";

	public static String CollNameQCRecord = "qcrecord";
	public static String DCQCRecord_id = "_id";
	public static String DCQCRecord_productname = "productname";
	public static String DCQCRecord_item = "checkeditem";
	public static String DCQCRecord_brand = "brand";
	public static String DCQCRecord_institude = "institude";
	public static String DCQCRecord_resulttext = "resulttext";
	public static String DCQCRecord_isFine = "isfine";

	public static String CollNameProduct = "product";
	public static String DCProduct_productId = "_id";
	public static String DCProduct_name = "name";
	public static String DCProduct_brand = "brand";
	public static String DCProduct_category = "category";
	public static String DCProduct_imgurls = "imgurls";
	public static String DCProduct_attributes = "attributes";
	public static String DCProduct_favourcount = "favourcount";

	public static String CollNameProductPrice ="productprice";
	public static String DCPrice_price = "price";
	public static String DCPrice_productid = "productid";
	public static String DCPrice_sourceUrl = "sourceUrl";
	public static String DCPrice_lastupdatedtime = "lastupdatedtime";
	public static String DCPrice_platform = "platform";

	public static String CollNameProductAnalysis = "productanalysis";
	public static String DCProAnalysis_productId = "_id";
	public static String DCProAnalysis_tags = "producttags";
	public static String DCProAnalysis_Keyrates = "keyrates";

	public static String CollNameProductMap = "productmap";
	public static String DCProMap_beforeid = "proidbefore";
	public static String DCProMap_afterid = "proidafter";

}
