package com.rockies.services.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.mapper.PackageInfoMapper;
import com.rockies.mapper.ProductInfoMapper;
import com.rockies.model.PackageInfo;
import com.rockies.services.IPackageInfoService;

@Service
public class PackageInfoServiceImpl implements IPackageInfoService {

	@Autowired
	private PackageInfoMapper packageInfoMapper;
	@Autowired
	private ProductInfoMapper productInfoMapper;

	@Override
	public int getCount(Map<String, Object> param) {
		int i = 0;
		try {
			i = packageInfoMapper.getCount(param);
		} catch (Exception e) {
			logger.error("login fail." + this.getClass() + ".getCount:" + e.toString());
		}
		return i;
	}

	@Override
	public boolean deleteByPrimaryKey(PackageInfo t) throws Exception {
		int i = 0;
		i = packageInfoMapper.deleteByPrimaryKey(t);
		i = productInfoMapper.deleteByPrimaryKey(t);
		if (i == 1) {
			return true;
		} else {
			throw new Exception("login fail." + this.getClass() + ".deleteByPrimaryKey:" + i);
		}
	}

	@Override
	public boolean insert(PackageInfo t) throws Exception {
		int i = 0;
		i = productInfoMapper.insert(t);
		i = packageInfoMapper.insert(t);
		if (i == 1) {
			return true;
		} else {
			throw new Exception("login fail." + this.getClass() + ".insert:" + i);
		}
	}

	@Override
	public boolean insertSelective(PackageInfo t) throws Exception {
		int i = 0;
		i = productInfoMapper.insertSelective(t);
		i = packageInfoMapper.insertSelective(t);
		if (i == 1) {
			return true;
		} else {
			throw new Exception("login fail." + this.getClass() + ".insertSelective:" + i);
		}
	}

	@Override
	public boolean updateByPrimaryKeySelective(PackageInfo t) throws Exception {
		int i = 0;
		i = productInfoMapper.updateByPrimaryKeySelective(t);
		i = packageInfoMapper.updateByPrimaryKeySelective(t);
		if (i == 1) {
			return true;
		} else {
			throw new Exception("login fail." + this.getClass() + ".updateByPrimaryKeySelective:" + i);
		}
	}

	@Override
	public boolean updateByPrimaryKey(PackageInfo t) throws Exception {
		int i = 0;
		i = productInfoMapper.updateByPrimaryKey(t);
		i = packageInfoMapper.updateByPrimaryKey(t);
		if (i == 1) {
			return true;
		} else {
			throw new Exception("login fail." + this.getClass() + ".updateByPrimaryKey:" + i);
		}
	}

	@Override
	public PackageInfo selectByPrimaryKey(PackageInfo t) {
		PackageInfo info = null;
		try {
			info = packageInfoMapper.selectByPrimaryKey(t);
		} catch (Exception e) {
			logger.error("login fail." + this.getClass() + ".selectByPrimaryKey:" + e.toString());
		}
		return info;
	}

	@Override
	public List<PackageInfo> selectAllListByParam(Map<String, Object> param) {
		List<PackageInfo> list = null;
		try {
			list = packageInfoMapper.selectAllListByParam(param);
		} catch (Exception e) {
			logger.error("login fail." + this.getClass() + ".selectAllListByParam:" + e.toString());
		}
		return list;
	}

	@Override
	public PackageInfo selectPackageInfoByParam(Map<String, Object> param) {
		PackageInfo info = null;
		try {
			info = packageInfoMapper.selectPackageInfoByParam(param);
		} catch (Exception e) {
			logger.error("login fail." + this.getClass() + ".selectPackageInfoByParam:" + e.toString());
		}
		return info;
	}

	@Override
	public List<Map<String, Object>> selectPackageListByParam(Map<String, Object> param) {
		List<Map<String, Object>> list = null;
		try {
			list = packageInfoMapper.selectPackageListByParam(param);
		} catch (Exception e) {
			logger.error("login fail." + this.getClass() + ".selectPackageListByParam:" + e.toString());
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> selectDaycnt() {
		List<Map<String, Object>> list = null;
		try {
			list = packageInfoMapper.selectDaycnt();
		} catch (Exception e) {
			logger.error("login fail." + this.getClass() + ".selectDaycnt:" + e.toString());
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> selectStaylvl() {
		List<Map<String, Object>> list = null;
		try {
			list = packageInfoMapper.selectStaylvl();
		} catch (Exception e) {
			logger.error("login fail." + this.getClass() + ".selectStaylvl:" + e.toString());
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> selectProperty() {
		List<Map<String, Object>> list = null;
		try {
			list = packageInfoMapper.selectProperty();
		} catch (Exception e) {
			logger.error("login fail." + this.getClass() + ".selectProperty:" + e.toString());
		}
		return list;
	}

	@Override
	public int countPackageListByParam(Map<String, Object> param) {
		int i = 0;
		try {
			i = packageInfoMapper.countPackageListByParam(param);
		} catch (Exception e) {
			logger.error("login fail." + this.getClass() + ".countPackageListByParam:" + e.toString());
		}
		return i;
	}

	private static final Logger logger = LoggerFactory.getLogger(PackageInfoServiceImpl.class);

	@Override
	public int getPackageCount() {
		int i = 0;
		try {
			i = packageInfoMapper.getPackageCount();
		} catch (Exception e) {
			logger.error("##############." + this.getClass() + ".getPackageCount:" + e.toString());
		}
		return i;
	}

	@Override
	public Integer getPackageListCount(Map<String, Object> param) {
		int i = 0;
		try {
			i = packageInfoMapper.getPackageListCount(param);
		} catch (Exception e) {
			logger.error("##############." + this.getClass() + ".getPackageListCount:" + e.toString());
		}
		return i;
	}

	@Override
	public PackageInfo selectByPrimaryKey(String pid) {
		return packageInfoMapper.selectByPrimaryKey(pid);

	}

}
