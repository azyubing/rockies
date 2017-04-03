package com.rockies.vo;

import com.rockies.model.HotelInfo;
import com.rockies.model.ProductInfo;

public class HotelInfoVo extends ProductInfo {

    private HotelInfo hotelInfo;
    private ProductInfo productInfo;
    
    public HotelInfo getHotelInfo() {
        return hotelInfo;
    }
    public void setHotelInfo(HotelInfo hotelInfo) {
        this.hotelInfo = hotelInfo;
    }
    public ProductInfo getProductInfo() {
        return productInfo;
    }
    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }
    
    
}
