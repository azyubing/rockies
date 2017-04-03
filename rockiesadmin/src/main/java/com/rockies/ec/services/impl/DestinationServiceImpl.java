package com.rockies.ec.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.ec.mapper.DestinationMapper;
import com.rockies.ec.model.DestinationProduct;
import com.rockies.ec.services.IDestinationService;
import com.rockies.ec.vo.DestinationVO;

@Service
public class DestinationServiceImpl implements IDestinationService {

	@Autowired
	private DestinationMapper destinationMapper;
	
	@Override
	public int insert(Object o) {
		destinationMapper.insert(o);
		return 0;
	}

	@Override
	public void deleteByPrimaryKey(int id) {
		destinationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Object> getAllList() {
		return destinationMapper.getAllList();
	}

	@Override
	public List<Object> getAllListByMap(Map map) {
		return destinationMapper.getAllListByMap(map);
	}

	@Override
	public List<Map<String, Object>> getAllListMap(Map map) {
		return destinationMapper.getAllListMap(map);
	}

	@Override
	public Object getOneByPrimaryKey(int id) {
		return destinationMapper.getOneByPrimaryKey(id);
	}

	@Override
	public long getCount(Map map) {
		return destinationMapper.getCount(map);
	}

	@Override
	public void updateObject(Object o) {
		destinationMapper.updateObject(o);
	}

	@Override
	public DestinationVO getDestinationVO(int id) {
		return destinationMapper.getDestinationVO(id);
	}

	@Override
	public boolean checkProductExist(Map map) {
		int count = destinationMapper.checkProductExist(map);
		if(count==0){
			return true;
		}
		return false;
	}

	@Override
	public void saveListDestinationProduc(String ids, int destinationId, int i) {
		String[] idList = ids.split(",");
		for(String id : idList){
			DestinationProduct destinationProduct = new DestinationProduct();
			destinationProduct.setCtdt(new Date());
			destinationProduct.setCtuser(i+"");
			destinationProduct.setProductId(id);
			destinationProduct.setDestinationId(destinationId);
			destinationMapper.saveDestinationProduct(destinationProduct);
		}
	}

	@Override
	public List<Map<String, Object>> getAllProductList(int id) {
		return destinationMapper.getAllProductList(id);
	}

	@Override
	public void deleteDestinationProduct(int id) {
		destinationMapper.deleteDestinationProduct(id);
	}

}
