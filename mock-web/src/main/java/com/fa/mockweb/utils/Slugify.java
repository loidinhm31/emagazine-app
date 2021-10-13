package com.fa.mockweb.utils;

public class Slugify {
	public static String convert(String name) {
		String code = name.replaceAll("[\\s\\/]", "-").toLowerCase();
		
		return code;
	}
}
