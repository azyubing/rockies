package com.rockies.mobile.utils;

public class DBUtils {
	public static int page(int page, int pageSize) {
		if (page >= 2) {
			return (page - 1) * pageSize;			
		} else {
			return 0;
		}
		
	}

	public static int pageSize(int page, int pageSize) {
		return pageSize;
	}
}
