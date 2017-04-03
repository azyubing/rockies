package com.rockies.ec.services;

import com.rockies.ec.services.base.IBaseInfoService;

public interface TestDemo<T> extends IBaseInfoService<T> {

    boolean getCountType(T t);
    
}
