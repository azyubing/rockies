package com.rockies.services;

import java.util.List;

import com.rockies.model.Media;
import com.rockies.services.base.BaseService;

public interface IMediaService extends BaseService {

	Media mediaDetail(Integer vid);

	List morePackage(List mediatags);

}
