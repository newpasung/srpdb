package srp.db.model;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import srp.mongodb.utils.Names;

public class InitialProduct extends CommonModel {

	private String id;
	private String name;
	private String brand;
	private String inicategory;
	private List<String> onlineimgurls;
	private String sourceurl;
	private List<String> onlinetags;
	private List<String> prices;
	private Map<String, String> attributes;
	private int dirty;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getInicategory() {
		return inicategory;
	}

	public void setInicategory(String inicategory) {
		this.inicategory = inicategory;
	}

	public List<String> getOnlineimgurls() {
		return onlineimgurls;
	}

	public void setOnlineimgurls(List<String> onlineimgurls) {
		this.onlineimgurls = onlineimgurls;
	}

	public String getSourceurl() {
		return sourceurl;
	}

	public void setSourceurl(String sourceurl) {
		this.sourceurl = sourceurl;
	}

	public List<String> getOnlinetags() {
		return onlinetags;
	}

	public void setOnlinetags(List<String> onlinetags) {
		this.onlinetags = onlinetags;
	}

	public List<String> getPrices() {
		return prices;
	}

	public void setPrices(List<String> prices) {
		this.prices = prices;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public int getDirty() {
		return dirty;
	}

	public void setDirty(int dirty) {
		this.dirty = dirty;
	}

	public static InitialProduct parseFromJson(String jsonStr) {
		try {
			JSONObject object = JSONObject.fromObject(jsonStr);
			return parseFromJson(object);
		} catch (JSONException e) {
			return null;
		}
	}

	public static InitialProduct parseFromJson(JSONObject object) {
		if (object.containsKey(Names.DCIniPro_productId)) {
			InitialProduct product = new InitialProduct();
			product.setId(parseString(object, Names.DCIniPro_productId));
			product.setName(parseString(object, "name"));
			product.setBrand(parseString(object, "brand"));
			product.setInicategory(parseString(object, "inicategory"));
			product.setSourceurl(parseString(object, "sourceurl"));
			product.setOnlineimgurls(parseList(object, "onlineimgurls"));
			product.setOnlinetags(parseList(object, "onlinetags"));
			product.setPrices(parseList(object, "prices"));
			product.setAttributes(parseMap(object, "attributes"));
			product.setDirty(parseInt(object, "dirty"));
			return product;
		} else {
			return null;
		}
	}

}
