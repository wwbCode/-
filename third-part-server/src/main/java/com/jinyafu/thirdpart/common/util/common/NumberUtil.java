package com.jinyafu.thirdpart.common.util.common;

import java.util.Random;

public class NumberUtil {
	/**
	 * 生成指定位数的随机数字符串
	 * @param length
	 * @return
	 */
	public static String getRandom(int length){		
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(String.valueOf(random.nextInt(10)));
		}
		return sb.toString();
	}
}