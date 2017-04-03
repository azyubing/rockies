package com.rockies.vo;

import java.util.List;

import com.rockies.model.BookCusInfo;
import com.rockies.model.ProductOrder;
import com.rockies.model.ProductOrderDetail;

/**
 * 产品订单
 */
public class ProductOrderVO {
	private ProductOrder productOrder;
	private List<ProductOrderDetail> ProductOrderDetailList;//产品订单详情
	private List<BookCusInfo> bookCusInfoList;//预定人信息
	public ProductOrder getProductOrder() {
		return productOrder;
	}
	public void setProductOrder(ProductOrder productOrder) {
		this.productOrder = productOrder;
	}
	public List<ProductOrderDetail> getProductOrderDetailList() {
		return ProductOrderDetailList;
	}
	public void setProductOrderDetailList(
			List<ProductOrderDetail> productOrderDetailList) {
		ProductOrderDetailList = productOrderDetailList;
	}
	public List<BookCusInfo> getBookCusInfoList() {
		return bookCusInfoList;
	}
	public void setBookCusInfoList(List<BookCusInfo> bookCusInfoList) {
		this.bookCusInfoList = bookCusInfoList;
	}
}
