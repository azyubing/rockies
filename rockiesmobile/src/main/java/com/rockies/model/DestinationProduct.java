package com.rockies.model;

/**
 * 目的地产品
 *
 */
public class DestinationProduct extends BaseModel {
	private int id;
	private int destinationId;//目的地id
	private String productId;//产品id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDestinationId() {
		return destinationId;
	}
	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	@Override
	public String toString() {
		return "DestinationProduct [id=" + id + ", destinationId="
				+ destinationId + ", productId=" + productId + "]";
	}
	
	
	
}
