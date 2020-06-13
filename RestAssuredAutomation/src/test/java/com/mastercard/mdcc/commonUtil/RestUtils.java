package com.mastercard.mdcc.commonUtil;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {
	
	public static String empName() {
		String randomString = RandomStringUtils.randomAlphabetic(8);
		return "John"+randomString;
	}
	
	public static String empSalary() {
		String randomString = RandomStringUtils.randomNumeric(6);
		return randomString;
	}
	
	public static String empAge() {
		String randomString = RandomStringUtils.randomNumeric(2);
		return "John"+randomString;
	}

}
