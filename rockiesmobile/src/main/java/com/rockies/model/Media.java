package com.rockies.model;

import java.util.List;

/**
 * 视屏
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
	private String img_path;
	private List mediatags;
	private List mediasimilar;
	private String timelong;
	
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	public List getMediasimilar() {
		return mediasimilar;
	}
	public void setMediasimilar(List mediasimilar) {
		this.mediasimilar = mediasimilar;
	}
	public List getMediatags() {
		return mediatags;
	}
	public void setMediatags(List mediatags) {
		this.mediatags = mediatags;
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
	@Override
	public String toString() {
		return "Media [id=" + id + ", name=" + name + ", title=" + title
				+ ", remark=" + remark + ", mediaUrl=" + mediaUrl
				+ ", packageUrl=" + packageUrl + ", mediaType=" + mediaType
				+ ", mediaTypeName=" + mediaTypeName + "]";
	}
	
}
