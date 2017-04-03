package com.rockies.mapper;

import java.util.Map;

import com.rockies.model.BookCusInfo;


public interface BookCusInfoMapper {
	public void saveBookCusInfo(BookCusInfo bookCusInfo);
	public void updateBookCusInfo(BookCusInfo bookCusInfo);
	public void deleteBookCusInfoById(int id);
	public void deleteBookCusInfoByParams(Map<String, Object> param);
	/**
	 * 订单同行人婴儿数量
	 * @return
	 */
	public int getBabyCount(String orderNo);
	/**
	 * 订单同行人儿童数量
	 * @return
	 */
	public int getChildCount(String orderNo);
	/**
	 * 订单同行人成人数量
	 * @return
	 */
	public int getAdultCount(String orderNo);
}
