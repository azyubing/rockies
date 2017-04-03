package com.rockies.ec.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.ec.mapper.CityMapper;
import com.rockies.ec.model.City;
import com.rockies.ec.services.ICityService;

@Service
public class CityServiceImpl implements ICityService {

	@Autowired
	private CityMapper cityMapper;
	@Override
	public int saveCity(City city) {
		city.setCtdt(new Date());
		cityMapper.saveCity(city);
		return city.getId();
	}

	@Override
	public void updateCity(City city) {
		city.setUpdt(new Date());
		cityMapper.updateCity(city);
	}

	@Override
	public void deleteCity(int id) {
		cityMapper.deleteCity(id);
	}

	@Override
	public List<City> getCityList(Map<String, Object> param) {
		return cityMapper.getCityList(param);
	}

    @Override
    public int getCityListCount(Map<String, Object> param) {
        return cityMapper.getCityListCount(param);
    }
	
	@Override
	public City getCity(int id) {
		return cityMapper.getCity(id);
	}

	@Override
	public Boolean checkCityCode(String cityCode) {
		int count = cityMapper.checkCityCode(cityCode);
		if(count==0){
			return true;
		}
		return false;
	}

	@Override
	public List<City> getCityByCityCode(String cityCode) {
		return cityMapper.getCityByCityCode(cityCode);
	}

	@Override
	public List<City> getCityTwo() {
		return cityMapper.getCityTwo();
	}



}
