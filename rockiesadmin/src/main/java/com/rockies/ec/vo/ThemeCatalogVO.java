package com.rockies.ec.vo;

import com.rockies.ec.model.BaseModel;

/**
 * 主题分类VO
 * @author Administrator
 *
 */
public class ThemeCatalogVO extends BaseModel{
	private int id;
	private String name;//分类名称
	private int themeId;//主题id
	private int hasProduct;//是否选择产品0是1否
	private String themeName;//主题名称
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
	public int getThemeId() {
		return themeId;
	}
	public void setThemeId(int themeId) {
		this.themeId = themeId;
	}
	public int getHasProduct() {
		return hasProduct;
	}
	public void setHasProduct(int hasProduct) {
		this.hasProduct = hasProduct;
	}
	public String getThemeName() {
		return themeName;
	}
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}
	@Override
	public String toString() {
		return "ThemeCatalogVO [id=" + id + ", name=" + name + ", themeId="
				+ themeId + ", hasProduct=" + hasProduct + ", themeName="
				+ themeName + "]";
	}
	
}
