package srp.mongodb.serviceimpls;

import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.codec.digest.DigestUtils;
import org.bson.Document;
import org.bson.types.ObjectId;

import srp.mongodb.main.JsonParsor;
import srp.mongodb.main.MongoProxy;
import srp.mongodb.services.UserService;
import srp.mongodb.utils.Names;
import srp.mongodb.utils.StatusCode;
import srp.mongodb.utils.UserHelper;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class UserServiceImpl implements UserService {

	@Override
	public String login(String account, String passwd) {
		String passwd_md5 = DigestUtils.md5Hex(passwd);
		Document document = MongoProxy.getCollAccount()
				.find(Filters.and(Filters.eq(Names.DCAccount_account, account),
						Filters.eq(Names.DCAccount_password, passwd_md5)))
				.first();
		if (document != null) {
			return UserHelper.printAll(document);
		}
		return JsonParsor.fail(StatusCode.INVALIDACCPASWD, "�˺Ż��������");
	}

	@Override
	public String register(String account, String passwd) {
		MongoCollection<Document> collection = MongoProxy.getCollAccount();
		Document oldData = collection.find(
				Filters.eq(Names.DCAccount_account, account))
				.first();
		if (oldData != null) {
			return JsonParsor.fail(StatusCode.DUPLICATE_ACCOUNT, "�˺��Ѿ���ʹ��");
		}
		String passwd_md5 = DigestUtils.md5Hex(passwd);
		try {
			// ��ʼ���û���Ϣ
			collection.insertOne(new Document()
					.append(Names.DCAccount_account,account)
					.append(Names.DCAccount_password, passwd_md5)
					.append(Names.DCAccount_created_time,
							new GregorianCalendar(Locale.CHINA)
									.getTimeInMillis())
					.append(Names.DCAccount_avatar, "")
					.append(Names.DCAccount_emailaddress, "")
					.append(Names.DCAccount_nickname, "�û�" + account));
		} catch (Exception e) {
			e.printStackTrace();
			return JsonParsor.fail(StatusCode.ERROR_FROM_DATABASE, "ϵͳ����δ��ע��");
		}
		Document newData = collection.find(Filters.eq(Names.DCAccount_account,
				account)).first();
		return UserHelper.printAll(newData);
	}

	@Override
	public String alterPasswd(String account, String oldPwd, String newPwd) {
		String oldPwd_md5 = DigestUtils.md5Hex(oldPwd);
		String newPwd_md5 = DigestUtils.md5Hex(newPwd);
		MongoCollection<Document> collection = MongoProxy.getCollAccount();
		Document oldData = collection.find(
				Filters.and(Filters.eq(Names.DCAccount_account, account)))
				.first();
		if (oldData == null) {
			return JsonParsor.fail(StatusCode.INVALIDPARAMETER, "�����벻��ȷ");
		}
		try {
			collection.updateOne(Filters.eq(Names.DCAccount_account, account),
					new Document("$set", new Document(Names.DCAccount_password,
							newPwd_md5)));
		} catch (Exception e) {
			e.printStackTrace();
			return JsonParsor.fail(StatusCode.ERROR_FROM_DATABASE,
					"ϵͳ�����޸�����ʧ��");
		}
		return JsonParsor.succeed("�����޸ĳɹ��������µ�¼");
	}

	@Override
	public String getDetailInfo(String account) {
		MongoCollection<Document> collection = MongoProxy.getCollAccount();
		Document userData = collection.find(
				Filters.eq(Names.DCAccount_account, account)).first();
		if (userData == null) {
			return JsonParsor.fail(StatusCode.INVALIDPARAMETER, "�û���Ϣ������");
		}
		return UserHelper.printAll(userData);
	}

	@Override
	public String updateInfo(String uid, String nickname, String mailAdd,
			String avatar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String favour(String productId, int isFavour, String account) {
		MongoCollection<Document> collection = MongoProxy.getCollProduct();
		Document oldData = collection.find(
				Filters.eq(Names.DCProduct_productId, new ObjectId(productId)))
				.first();
		int favourCount = oldData.getInteger(Names.DCProduct_favourcount, 0);
		if (favourCount == 0 && isFavour == 0) {
			return JsonParsor.fail(StatusCode.OPERATION_STATUS_CONFLICT,
					"�޷�ȡ����");
		}
		try {
			collection.updateMany(Filters.eq(Names.DCProduct_productId,
					new ObjectId(productId)), new Document().append("$set",
					new Document(Names.DCProduct_favourcount, favourCount
							+ isFavour)));
		} catch (Exception e) {
			return JsonParsor.fail(StatusCode.ERROR_FROM_DATABASE, "����ʧ��");
		}
		return JsonParsor.succeed("�����ɹ�");
	}

}
