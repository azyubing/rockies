package com.rockies.model;

/**
 * 主题
 *
 */
public class Theme extends BaseModel {
	private int id;
	private String name;// 主题名称
	private int isHome;// 是否首页展示0是1否
	private int homeShowOrder;// 主页显示顺序
	private int isMore;// 是否更多展示0是1否
	private int moreShowOrder;// 更多展示顺序
	private String img;// icon of theme

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

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

	@Override
	public String toString() {
		return "Theme [id=" + id + ", name=" + name + ", isHome=" + isHome + ", homeShowOrder=" + homeShowOrder
				+ ", isMore=" + isMore + ", moreShowOrder=" + moreShowOrder + ", img=" + img +"]";
	}

}
