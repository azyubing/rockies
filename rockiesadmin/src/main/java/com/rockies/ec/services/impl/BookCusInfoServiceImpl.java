package com.rockies.ec.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.ec.mapper.BookCusInfoMapper;
import com.rockies.ec.model.BookCusInfo;
import com.rockies.ec.services.IBookCusInfoService;

@Service
public class BookCusInfoServiceImpl implements IBookCusInfoService {

	@Autowired
	private BookCusInfoMapper bookCusInfoMapper;
	
	@Override
	public void saveBookCusInfo(BookCusInfo bookCusInfo) {
		bookCusInfoMapper.saveBookCusInfo(bookCusInfo);
	}

	@Override
	public void updateBookCusInfo(BookCusInfo bookCusInfo) {
		bookCusInfoMapper.updateBookCusInfo(bookCusInfo);
	}

	@Override
	public int getBabyCount(String orderNo) {
		return bookCusInfoMapper.getBabyCount(orderNo);
	}

	@Override
	public int getChildCount(String orderNo) {
		return bookCusInfoMapper.getChildCount(orderNo);
	}

	@Override
	public int getAdultCount(String orderNo) {
		return bookCusInfoMapper.getAdultCount(orderNo);
	}

	@Override
	public List<Map<String, Object>> getBaby(String orderNo) {
		return bookCusInfoMapper.getBaby(orderNo);
	}

	@Override
	public List<Map<String, Object>> getChild(String orderNo) {
		return bookCusInfoMapper.getChild(orderNo);
	}

	@Override
	public List<Map<String, Object>> getAdult(String orderNo) {
		return bookCusInfoMapper.getAdult(orderNo);
	}

}
