package com.rockies.mapper;

import com.rockies.model.ProductOrderDetail;

public interface ProductOrderDetailMapper {
	public void saveProductOrderDetail(ProductOrderDetail productOrderDetail);

	public void deleteProductOrderDetail(String orderNo);
}
