package srp.db.model;

import java.util.List;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import srp.mongodb.utils.Names;

public class IniComment extends CommonModel {
	private String id;
	private String content;
	private String time;
	private String commentor;
	private String source;
	private List<String> imgsincomment;
	private String avatar;
	private String productId;
	private String rate;
	private int dirtyfilter;
	private int dirtyemoanalysis;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCommentor() {
		return commentor;
	}

	public void setCommentor(String commentor) {
		this.commentor = commentor;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<String> getImgsincomment() {
		return imgsincomment;
	}

	public void setImgsincomment(List<String> imgsincomment) {
		this.imgsincomment = imgsincomment;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public int getDirtyfilter() {
		return dirtyfilter;
	}

	public void setDirtyfilter(int dirtyfilter) {
		this.dirtyfilter = dirtyfilter;
	}

	public int getDirtyemoanalysis() {
		return dirtyemoanalysis;
	}

	public void setDirtyemoanalysis(int dirtyemoanalysis) {
		this.dirtyemoanalysis = dirtyemoanalysis;
	}

	public static IniComment parseFromJson(String jsonStr) {
		try {
			JSONObject object = JSONObject.fromObject(jsonStr);
			return parseFromJson(object);
		} catch (JSONException e) {
			return null;
		}
	}

	public static IniComment parseFromJson(JSONObject json) {
		if (json.containsKey(Names.DCComment_commentid)) {
			IniComment iniComment;
			iniComment = new IniComment();
			iniComment.setId(parseString(json, Names.DCIniComment_commentid));
			iniComment.setContent(parseString(json, "content"));
			iniComment.setTime(parseString(json, "time"));
			iniComment.setCommentor(parseString(json, "commentor"));
			iniComment.setSource(parseString(json, "source"));
			iniComment.setImgsincomment(parseList(json, "imgsincomment"));
			iniComment.setAvatar(parseString(json, "avatar"));
			iniComment.setProductId(parseString(json, "productId"));
			iniComment.setRate("rate");
			iniComment.setDirtyfilter(parseInt(json, "dirtyfilter"));
			iniComment.setDirtyemoanalysis(parseInt(json, "dirtyemoanalysis"));
			return iniComment;
		}
		return null;
	}

}
