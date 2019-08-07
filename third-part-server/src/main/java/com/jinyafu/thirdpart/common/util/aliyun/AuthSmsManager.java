package com.jinyafu.thirdpart.common.util.aliyun;

import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

/**
 * 短信管理
 */
@Service
public class AuthSmsManager {
	private final long validTime = 60 * 1000;//有效时长，1分钟
	
	Map<String, Long> timeMap = new java.util.concurrent.ConcurrentHashMap<String, Long>();//<手机号，过期时间>
	Map<String, String> smsCodeMap = new java.util.concurrent.ConcurrentHashMap<String, String>();//<手机号，验证码>

	/**
	 * 保存验证码
	 * @param phone 手机号
	 * @param code 验证码
	 */
	public void putSmsCode(String mobile, String code) {
		smsCodeMap.put(mobile, code);
		timeMap.put(mobile, System.currentTimeMillis() + validTime);
	}
	
	/**
	 * 保存验证码
	 * @param phone 手机号
	 * @param code 验证码
	 * @param userValidTime 自定义有限时长，单位:秒
	 */
	public void putSmsCode(String mobile, String code, long userValidTime) {
		smsCodeMap.put(mobile, code);
		timeMap.put(mobile, System.currentTimeMillis() + userValidTime * 1000);
	}
	
	/**
	 * 验证是否有效
	 * @param phone 手机号
	 * @param code 验证码
	 * @return 1有效，-1验证码错误，-2已失效
	 */
	public int isValid(String mobile, String code) {
		long now = System.currentTimeMillis();
		Long validTime = timeMap.get(mobile);//过期时间
		if(validTime==null || validTime < now) {
			return -2;
		} 
		String validCode = smsCodeMap.get(mobile);
		if(validCode==null) {
			return -2;
		} else if(!validCode.equals(code)) {
			return -1;
		}
		return 1;
	}
	
	/**
	 * 清除过期
	 * @param times
	 */
	public void clearTimeOut() {
		Set<String> keySet = timeMap.keySet();
		long now = System.currentTimeMillis();
		for (String mobile : keySet) {
			Long lastTime = timeMap.getOrDefault(mobile, 0L);
			long interval = now - lastTime;
			if (interval >= 0) {
				timeMap.remove(mobile);	
				smsCodeMap.remove(mobile);
			}
		}
	}
}