package com.rockies.services;

import com.rockies.services.base.IBaseInfoService;

public interface TestDemo<T> extends IBaseInfoService<T> {

    boolean getCountType(T t);
    
}
