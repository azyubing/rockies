package com.rockies.ec.model;

/**
 * 
 * 主题分类产品
 * @author hyh
 *
 */
public class ThemeProduct extends BaseModel{
	private int id;//主题产品id
	private int themeId;//主题id
	private String productId;//产品id
	private int isHomeShow;//是否主页显示0是1否
	private String isThemeHomeShow;
	private int themeCatalogId;//主题分类id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getThemeId() {
		return themeId;
	}
	public void setThemeId(int themeId) {
		this.themeId = themeId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getIsHomeShow() {
		return isHomeShow;
	}
	public void setIsHomeShow(int isHomeShow) {
		this.isHomeShow = isHomeShow;
	}
	public int getThemeCatalogId() {
		return themeCatalogId;
	}
	public void setThemeCatalogId(int themeCatalogId) {
		this.themeCatalogId = themeCatalogId;
	}

	public String getIsThemeHomeShow() {
        return isThemeHomeShow;
    }
    public void setIsThemeHomeShow(String isThemeHomeShow) {
        this.isThemeHomeShow = isThemeHomeShow;
    }
    @Override
    public String toString() {
        return "ThemeProduct [id=" + id + ", themeId=" + themeId + ", productId=" + productId
                + ", isHomeShow=" + isHomeShow + ", isThemeHomeShow=" + isThemeHomeShow
                + ", themeCatalogId=" + themeCatalogId + "]";
    }
	
	
}
