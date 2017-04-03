package com.rockies.ec.vo;

import java.util.List;

import com.rockies.ec.model.BookCusInfo;
import com.rockies.ec.model.ProductOrder;
import com.rockies.ec.model.ProductOrderDetail;

/**
 * 产品订单
 * @author hyh
 *
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
