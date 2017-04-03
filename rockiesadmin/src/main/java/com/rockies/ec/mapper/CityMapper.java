package com.rockies.ec.mapper;

import java.util.List;
import java.util.Map;

import com.rockies.ec.model.City;

public interface CityMapper {
	/**
	 * 添加
	 * @param city
	 */
	public void saveCity(City city);
	/**
	 * 修改
	 * @param city
	 */
	public void updateCity(City city);
	/**
	 * 删除，直接删除数据
	 * @param city
	 */
	public void deleteCity(int id);
	/**
	 * 获取父类下的城市/区域列表
	 * @param parentId
	 * @return
	 */
	public List<City> getCityList(Map<String,Object> param);
	/**
     * 获取父类下的城市/区域列表数
     */
    public int getCityListCount(Map<String,Object> param);
	/**
	 * 查找城市
	 * @param id
	 * @return
	 */
	public City getCity(int id);
	/**
	 * 验证cityCode是否存在
	 * @param cityCode
	 * @param language
	 * @return
	 */
	public int checkCityCode(String cityCode);
	
	/**
	 * 通过城市代码查找同一层次的城市列表
	 * @param cityCode
	 * @param language
	 * @return
	 */
	public List<City> getCityByCityCode(String cityCode);
	
	/**
	 * 获取所有的二级城市
	 * @return
	 */
	public List<City> getCityTwo();
	

}
