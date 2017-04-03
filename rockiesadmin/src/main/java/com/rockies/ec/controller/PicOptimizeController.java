package com.rockies.ec.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rockies.ec.services.IpicOptimizeService;

/**
 * @author Administrator
 * 
 */
@Controller
public class PicOptimizeController {

	private static final Logger logger = LoggerFactory.getLogger(PicOptimizeController.class);
	
	@Autowired
	IpicOptimizeService picOptimizeService;
	
	@RequestMapping(value = "/pic/syncCompressPic")
	public ModelAndView bannerAdmin(HttpServletResponse response,
			HttpServletRequest request) {
		logger.info("触发图片同步");
		picOptimizeService.synchronizeCompressFiles();
		return null;
	}
}
