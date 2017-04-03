package com.rockies.services.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.mapper.RoomPriceInfoMapper;
import com.rockies.model.PackagePriceInfo;
import com.rockies.model.RoomPriceInfo;
import com.rockies.services.IRoomPriceInfoService;

@Service
public class RoomPriceInfoServiceImpl implements IRoomPriceInfoService {

	@Autowired
	private RoomPriceInfoMapper roomPriceInfoMapper;

	@Override
	public boolean deleteByPrimaryKey(RoomPriceInfo t) throws Exception {
		int i = 0;
		i = roomPriceInfoMapper.deleteByPrimaryKey(t);
		if (i == 1) {
			return true;
		} else {
			throw new Exception(this.getClass() + ".deleteByPrimaryKey:" + i);
		}
	}

	@Override
	public boolean insert(RoomPriceInfo record) throws Exception {
		int i = 0;
		i = roomPriceInfoMapper.insert(record);
		if (i == 1) {
			return true;
		} else {
			throw new Exception(this.getClass() + ".insert:" + i);
		}
	}

	@Override
	public boolean insertSelective(RoomPriceInfo record) throws Exception {
		int i = 0;
		i = roomPriceInfoMapper.insertSelective(record);
		if (i == 1) {
			return true;
		} else {
			throw new Exception(this.getClass() + ".insertSelective:" + i);
		}
	}

	@Override
	public RoomPriceInfo selectByPrimaryKey(RoomPriceInfo record) {
		RoomPriceInfo roomPriceInfo = null;
		try {
			roomPriceInfo = roomPriceInfoMapper.selectByPrimaryKey(record);
		} catch (Exception e) {
			logger.error("login fail." + this.getClass() + ".selectByPrimaryKey:" + e.toString());
		}
		return roomPriceInfo;
	}

	@Override
	public List<RoomPriceInfo> selectAllListByParam(Map<String, Object> param) {
		List<RoomPriceInfo> list = null;
		try {
			list = roomPriceInfoMapper.selectAllListByParam(param);
		} catch (Exception e) {
			logger.error("login fail." + this.getClass() + ".selectAllListByParam:" + e.toString());
		}
		return list;
	}

	@Override
	public boolean updateByPrimaryKeySelective(RoomPriceInfo record) throws Exception {
		int i = 0;
		i = roomPriceInfoMapper.updateByPrimaryKeySelective(record);
		if (i == 1) {
			return true;
		} else {
			throw new Exception(this.getClass() + ".updateByPrimaryKeySelective:" + i);
		}
	}

	@Override
	public boolean updateByPrimaryKey(RoomPriceInfo record) throws Exception {
		int i = 0;
		i = roomPriceInfoMapper.updateByPrimaryKey(record);
		if (i == 1) {
			return true;
		} else {
			throw new Exception(this.getClass() + ".updateByPrimaryKey:" + i);
		}
	}

	@Override
	public int getCount(Map<String, Object> param) {
		int i = 0;
		try {
			i = roomPriceInfoMapper.getCount(param);
		} catch (Exception e) {
			logger.error("login fail." + this.getClass() + ".getCount:" + e.toString());
		}
		return i;
	}

	private static final Logger logger = LoggerFactory.getLogger(RoomPriceInfoServiceImpl.class);

	@Override
	public RoomPriceInfo selectRoomPriceInfoById(int id) {
		return roomPriceInfoMapper.selectRoomPriceInfoById(id);
	}

	@Override
	public List<PackagePriceInfo> gethotelPriceCal(Map<String, Object> param) {
		return roomPriceInfoMapper.gethotelPriceCal(param);
	}

	@Override
	public RoomPriceInfo queryPrice(Map<String, Object> param) {
		return roomPriceInfoMapper.queryPrice(param);
	}

}
