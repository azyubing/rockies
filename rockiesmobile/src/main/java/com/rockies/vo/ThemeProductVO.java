package com.rockies.vo;

import com.rockies.model.BaseModel;

/**
 * 主题分类产品VO
 */
public class ThemeProductVO extends BaseModel {
	private int id;//主题产品id
	private String productName;//主题产品名称
	private int themeId;//主题id
	private String themeName;//主题名称
	private int productId;//产品id
	private int IsHomeShow;//是否主页显示0是1否
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getThemeId() {
		return themeId;
	}
	public void setThemeId(int themeId) {
		this.themeId = themeId;
	}
	public String getThemeName() {
		return themeName;
	}
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getIsHomeShow() {
		return IsHomeShow;
	}
	public void setIsHomeShow(int isHomeShow) {
		IsHomeShow = isHomeShow;
	}
	@Override
	public String toString() {
		return "ThemeProductVO [id=" + id + ", productName=" + productName
				+ ", themeId=" + themeId + ", themeName=" + themeName
				+ ", productId=" + productId + ", IsHomeShow=" + IsHomeShow
				+ "]";
	}
	
}
