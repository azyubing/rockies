package com.rockies.ec.services;

import java.util.List;
import java.util.Map;

import com.rockies.ec.model.BookCusInfo;

public interface IBookCusInfoService {
	public void saveBookCusInfo(BookCusInfo bookCusInfo);
	public void updateBookCusInfo(BookCusInfo bookCusInfo);
	
	/**
	 * 订单同行人婴儿数量
	 * @return
	 */
	public int getBabyCount(String orderNo);
	public List<Map<String, Object>> getBaby(String orderNo);
	/**
	 * 订单同行人儿童数量
	 * @return
	 */
	public int getChildCount(String orderNo);
	public List<Map<String, Object>> getChild(String orderNo);
	/**
	 * 订单同行人成人数量
	 * @return
	 */
	public int getAdultCount(String orderNo);
	public List<Map<String, Object>> getAdult(String orderNo);
}
