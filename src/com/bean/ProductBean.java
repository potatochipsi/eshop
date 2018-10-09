package com.bean;

import java.util.List;

/**
 * 商品Bean
 * @author 胡建峰
 *
 */
public class ProductBean {
	
	private int id;
	private String name;
	private float originalPrice;
	private float price;
	private String intro;
	private int productTypeId;
	private ProductTypeBean productTypeBean;
	private int number;
	private int sellNumber;
	private String pic;
	private String productProperties;
	private List<ProductOptionBean> productOptionBeans;
	private String createDate;

	//构造
	public ProductBean() {
	}
	
	public ProductBean(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public ProductBean(int id, String name, float originalPrice, float price, String intro, int productTypeId,
			int number, int sellNumber, String pic, String productProperties, String createDate) {
		this.id = id;
		this.name = name;
		this.originalPrice = originalPrice;
		this.price = price;
		this.intro = intro;
		this.productTypeId = productTypeId;
		this.number = number;
		this.sellNumber = sellNumber;
		this.pic = pic;
		this.productProperties = productProperties;
		this.createDate = createDate;
	}

	public ProductBean(int id, String name, float price, int number, String pic) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.number = number;
		this.pic = pic;
	}

	//get  set
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(float originalPrice) {
		this.originalPrice = originalPrice;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public int getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(int productTypeId) {
		this.productTypeId = productTypeId;
	}
	public ProductTypeBean getProductTypeBean() {
		return productTypeBean;
	}
	public void setProductTypeBean(ProductTypeBean productTypeBean) {
		this.productTypeBean = productTypeBean;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getSellNumber() {
		return sellNumber;
	}
	public void setSellNumber(int sellNumber) {
		this.sellNumber = sellNumber;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getProductProperties() {
		return productProperties;
	}
	public void setProductProperties(String productProperties) {
		this.productProperties = productProperties;
	}
	public List<ProductOptionBean> getProductOptionBeans() {
		return productOptionBeans;
	}
	public void setProductOptionBeans(List<ProductOptionBean> productOptionBeans) {
		this.productOptionBeans = productOptionBeans;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "ProductBean [id=" + id + ", name=" + name + ", originalPrice=" + originalPrice + ", price=" + price
				+ ", intro=" + intro + ", productTypeId=" + productTypeId + ", productTypeBean=" + productTypeBean
				+ ", number=" + number + ", sellNumber=" + sellNumber + ", pic=" + pic + ", productProperties="
				+ productProperties + ", createDate=" + createDate + "]";
	}
}
