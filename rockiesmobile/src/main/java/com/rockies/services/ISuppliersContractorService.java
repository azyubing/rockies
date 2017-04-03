package com.rockies.services;

import java.util.List;
import java.util.Map;

import com.rockies.model.SuppliersContractorBean;


public interface ISuppliersContractorService {
	/**
	 * 添加
	 * @param suppliersContractorBean
	 */
	public void save(SuppliersContractorBean suppliersContractorBean);
	
	/**
	 * 添加
	 * @param suppliersContractorBean
	 */
	public void update(SuppliersContractorBean suppliersContractorBean);
	
	/**
	 * 查找
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getSuppliersContractorBeanList(Map map);
	
	/**
	 * 总记录数
	 * @param map
	 * @return
	 */
	public int getCount(Map map);

	/**
	 * 查找供应商联系人
	 * @param cid
	 * @return
	 */
	public SuppliersContractorBean getSuppliersContractor(int cid);
	
	/**
	 * 删除
	 * @param cid
	 */
	public void deleteByCid(int cid);
	/**
	 * 查找供应商联系人
	 * @param sid
	 * @return
	 */
	public SuppliersContractorBean getSuppliersContractorBySid(long sid);
}
