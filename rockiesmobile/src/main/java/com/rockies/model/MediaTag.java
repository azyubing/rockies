package com.rockies.model;

/**
 * 视屏标签
 *
 */
public class MediaTag extends BaseModel {
	private int id;
	private String name;//标签名称
	private int mediaId;//视屏id
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
	public int getMediaId() {
		return mediaId;
	}
	public void setMediaId(int mediaId) {
		this.mediaId = mediaId;
	}
	@Override
	public String toString() {
		return "MediaTag [id=" + id + ", name=" + name + ", mediaId=" + mediaId
				+ "]";
	}
	
	
}
