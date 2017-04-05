package com.rockies.webservice.service.base;

import java.util.List;
import java.util.Map;

public interface IBaseInfoService<T> {
    
    int getCount(Map<String,Object> param);
    
    boolean deleteByPrimaryKey(T t) throws Exception;

    boolean insert(T t) throws Exception;

    boolean insertSelective(T t) throws Exception;

    boolean updateByPrimaryKeySelective(T t) throws Exception;

    boolean updateByPrimaryKey(T t) throws Exception;
    
    T selectByPrimaryKey(T t);
    
    List<T> selectAllListByParam(Map<String,Object> param);  
    
    
}
