package com.rockies.ec.model;

/**
 * 主题
 * @author hyh
 *
 */
public class Theme extends BaseModel{
	private int id;
	private String name;//主题名称
	private int isHome;//是否首页展示0是1否
	private int homeShowOrder;//主页显示顺序
	private int isMore;//是否更多展示0是1否
	private int moreShowOrder;//更多展示顺序
	private String name_en;//英文名
	private String description;//主题描述
	
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
	public int getIsHome() {
		return isHome;
	}
	public void setIsHome(int isHome) {
		this.isHome = isHome;
	}
	public int getHomeShowOrder() {
		return homeShowOrder;
	}
	public void setHomeShowOrder(int homeShowOrder) {
		this.homeShowOrder = homeShowOrder;
	}
	public int getIsMore() {
		return isMore;
	}
	public void setIsMore(int isMore) {
		this.isMore = isMore;
	}
	public int getMoreShowOrder() {
		return moreShowOrder;
	}
	public void setMoreShowOrder(int moreShowOrder) {
		this.moreShowOrder = moreShowOrder;
	}
	public String getName_en() {
		return name_en;
	}
	public void setName_en(String name_en) {
		this.name_en = name_en;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Theme [id=" + id + ", name=" + name + ", isHome=" + isHome
				+ ", homeShowOrder=" + homeShowOrder + ", isMore=" + isMore+ ", name_en=" + name_en+ ", description=" + description
				+ ", moreShowOrder=" + moreShowOrder + "]";
	}
	
}
