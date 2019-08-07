package com.jinyafu.thirdpart.common.util.aliyun;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;

/**
 * 短信接口
 */
@Service

public class SmsManager {
    protected final Log logger = LogFactory.getLog(getClass());
	/**
     * 阿里云发送短信
     * @param templateCode (*必填)短信模板编码
     * @param phones (*必填)待发送手机号，可以多个","分隔。境外手机号00+区号+号码
     * @param param 代换模板中变量参数，EnumMap格式，只能从指定 enum 中选择值，避免用户自已填变量名出错
     * @return
     * @throws ClientException
     */
	public boolean send(String templateCode, String phones, Map<String, String> param) {
		boolean isSendSuccess = true;
		try {
			SendSmsResponse response = new AliyunSms().send(templateCode, phones, param);
			
			if(!response.getCode().equals(AliyunSms.RETURN_CODE_OK)) {
				isSendSuccess = false;
	    	}			
		} catch (NullPointerException nullE) {
			isSendSuccess = false;
		} catch (ClientException ce) {
			isSendSuccess = false;
		} catch (Exception e) {
            e.printStackTrace();
        }
		return isSendSuccess;
	}
	
	/**
     * 查询阿里云短信发送情况
     * @param phone (*必填)手机号
     * @param bizId 发送流水号,可以从调用发送接口返回值中获取
     * @param sendDate 短信发送日期格式yyyyMMdd,支持最近30天记录查询
     * @param pageSize 默认10
     * @param currentPage
     * @return
     * @throws ClientException
     */
    public QuerySendDetailsResponse querySendDetails(String phones, String bizId, String sendDate, long pageSize, long currentPage) 
    	throws NullPointerException, ClientException {
    	return new AliyunSms().querySendDetails(phones, bizId, sendDate, pageSize, currentPage);
    }    
}	