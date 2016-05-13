package srp.db.model;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class Product extends CommonModel {
	private String id;
	private String name;
	private String brand;
	private String category;
	private List<String> imgurls;
	private Map<String, String> attributes;
	private int favourcount;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<String> getImgurls() {
		return imgurls;
	}

	public void setImgurls(List<String> imgurls) {
		this.imgurls = imgurls;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public int getFavourcount() {
		return favourcount;
	}

	public void setFavourcount(int favourcount) {
		this.favourcount = favourcount;
	}

	public static Product parseFromJson(String jsonStr) {
		try {
			JSONObject object = JSONObject.fromObject(jsonStr);
			return parseFromJson(object);
		} catch (JSONException e) {
			return null;
		}
	}

	public static Product parseFromJson(JSONObject object) {
		if (object.containsKey("_id")) {
			Product product = new Product();
			product.setId(parseString(object, "_id"));
			product.setName(parseString(object, "name"));
			product.setBrand(parseString(object, "brand"));
			product.setCategory(parseString(object, "category"));
			product.setImgurls(parseList(object, "imgurls"));
			product.setAttributes(parseMap(object, "attributes"));
			product.setFavourcount(parseInt(object, "favourcount"));
			return product;
		} else {
			return null;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("id:");
		builder.append(getId());
		builder.append("name:");
		builder.append(getName());
		builder.append("brand:");
		builder.append(getBrand());
		return builder.toString();
	}

}
