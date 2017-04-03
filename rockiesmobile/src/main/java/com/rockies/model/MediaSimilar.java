package com.rockies.model;

/**
 * 相似套餐
 *
 */
public class MediaSimilar extends BaseModel {
	private int id;
	private int mediaId;//视屏id
	private String productId;//套餐id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMediaId() {
		return mediaId;
	}
	public void setMediaId(int mediaId) {
		this.mediaId = mediaId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	@Override
	public String toString() {
		return "MediaSimilar [id=" + id + ", mediaId=" + mediaId
				+ ", productId=" + productId + "]";
	}
	
}
