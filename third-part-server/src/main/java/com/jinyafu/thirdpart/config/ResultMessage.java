package com.jinyafu.thirdpart.config;

import com.onlyxiahui.common.message.result.AbstractResultMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XiaHui date 2016-05-19 16:12:15
 */
public class ResultMessage extends AbstractResultMessage<Map<String, Object>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String, Object> body = new HashMap<String, Object>();

	public ResultMessage() {
		super();
		this.putValue("body", body);
	}

	public ResultMessage(Map<String, Object> body) {
		this.body = body;
		this.putValue("body", body);
	}

	public ResultMessage(Object data) {
		body.put("data", data);
	}

	public Map<String, Object> getBody() {
		return body;
	}

	public Map<String, Object> setBody(Map<String, Object> body) {
		this.putValue("body", body);
		return this.body = body;
	}

	public Object put(String key, Object value) {
		return body.put(key, value);
	}

	public Object get(String key) {
		return body.get(key);
	}

}
