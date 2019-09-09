package com.jinyafu.thirdpart.common.data;

import com.onlyxiahui.common.message.Head;

public class ResultHead extends Head{
	
	private String resultCode;
	private String resultMessage;
	
	public ResultHead() {
		resultCode = code_success;
		resultMessage = "";
	}
	
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
}
