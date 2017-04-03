package com.rockies.mapper.base;

import java.util.List;
import java.util.Map;

public interface IBaseInfoMapper<T> {
    
    int deleteByPrimaryKey(T t);

    int insert(T t);

    int insertSelective(T t);

    T selectByPrimaryKey(T t);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);
    
    List<T> selectAllListByParam(Map<String, Object> param);
    
    int getCount(Map<String, Object> param);
}
