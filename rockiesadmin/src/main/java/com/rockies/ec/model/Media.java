package com.rockies.ec.model;

/**
 * 视屏
 * @author hyh
 *
 */
public class Media extends BaseModel {
	private int id ;//id
	private String name;//名称
	private String title;//标题
	private String remark;//介绍
	private String mediaUrl;//视屏URL
	private String packageUrl;//套餐URL
	private int mediaType;//视频来源类型
	private String mediaTypeName;//视频来源名称
	private String timelong;//视频时长
	private String img_path;//图片
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMediaUrl() {
		return mediaUrl;
	}
	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}
	public String getPackageUrl() {
		return packageUrl;
	}
	public void setPackageUrl(String packageUrl) {
		this.packageUrl = packageUrl;
	}
	public int getMediaType() {
		return mediaType;
	}
	public void setMediaType(int mediaType) {
		this.mediaType = mediaType;
	}
	public String getMediaTypeName() {
		return mediaTypeName;
	}
	public void setMediaTypeName(String mediaTypeName) {
		this.mediaTypeName = mediaTypeName;
	}
	
	public String getTimelong() {
		return timelong;
	}
	public void setTimelong(String timelong) {
		this.timelong = timelong;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	@Override
	public String toString() {
		return "Media [id=" + id + ", name=" + name + ", title=" + title
				+ ", remark=" + remark + ", mediaUrl=" + mediaUrl
				+ ", packageUrl=" + packageUrl + ", mediaType=" + mediaType
				+ ", mediaTypeName=" + mediaTypeName + ", timelong=" + timelong
				+ ", img_path=" + img_path + "]";
	}
	
}
