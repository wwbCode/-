package com.jinyafu.thirdpart.common.util.aliyun;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import net.minidev.json.JSONObject;


/**
 * 阿里云短信
 */
public class AliyunSms {
	protected final Log logger = LogFactory.getLog(getClass());
	
	//配置属性
    static final String product = "Dysmsapi";//产品名称:云通信短信API产品,开发者无需替换    
    static final String domain = "dysmsapi.aliyuncs.com";//产品域名,开发者无需替换
    
    //返回代码
    public static final String RETURN_CODE_OK = "OK"; 
    
    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAION03v3ViY3Ww";
    static final String accessKeySecret = "YMBJ7iKgCJ4yiYob41ocVfoTB1xQCD"; 
    static final String sign = "金雅福商城";//签名
    
    //超时时长
    static final String timeOut = "10000";//连接超时
    static final String area = "cn-shenzhen";//区域
        
    private IAcsClient acsClient;//镜像对象  
    
    /**
     * 构造函数，初始化镜像对象
     */
    public AliyunSms() {
    	//可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", timeOut);
        System.setProperty("sun.net.client.defaultReadTimeout", timeOut);

        //初始化acsClient,暂不支持region化
        try {        	
        	IClientProfile profile = DefaultProfile.getProfile(area, accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint(area, area, product, domain);
            acsClient = new DefaultAcsClient(profile);
        } catch (ClientException e) {
        	logger.error("初始化阿里云sms镜像对象出错", e);
        }        
    }
    
    /**
     * 发送短信
     * @param templateCode (*必填)短信模板编码
     * @param phones (*必填)待发送手机号，可以多个","分隔。境外手机号00+区号+号码
     * @param param 代换模板中变量参数，EnumMap格式，只能从指定 enum 中选择值，避免用户自已填变量名出错
     * @return
     * @throws ClientException
     */
    public SendSmsResponse send(String templateCode, String phones, Map<String, String> param) 
    		throws NullPointerException, ClientException {
    	if((templateCode==null || templateCode.isEmpty()) || 
    		(phones==null || phones.isEmpty())) {
    		throw new NullPointerException();
    	}
    	
    	Optional<IAcsClient> optAcsClient = Optional.of(acsClient);    	
    	//组装请求对象-具体描述见控制台-文档部分内容
    	SendSmsRequest request = new SendSmsRequest();
    	//必填:待发送手机号，可以","分隔
    	request.setPhoneNumbers(phones);
    	//必填:短信签名-可在短信控制台中找到
    	request.setSignName(sign);
    	//必填:短信模板-可在短信控制台中找到
    	request.setTemplateCode(templateCode);
    	//可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
    	request.setTemplateParam(JSONObject.toJSONString(param));
    	
    	SendSmsResponse sendSmsResponse = optAcsClient.get().getAcsResponse(request);    	
    	return sendSmsResponse;    
    }    
    
    /**
     * 查询短信发送情况
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
    	if(phones==null || phones.isEmpty()) {
    		throw new NullPointerException();
    	}
    	if(sendDate==null || sendDate.isEmpty()) {
    		LocalDate today = LocalDate.now();
        	DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE; 
        	sendDate = today.format(formatter);
    	}
    	
    	Optional<IAcsClient> optAcsClient = Optional.of(acsClient);
        //组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码
        request.setPhoneNumber(phones);
        //可选-流水号
        request.setBizId(bizId);
        //必填-发送日期 支持30天内记录查询，格式yyyyMMdd    
        request.setSendDate(sendDate);
        //必填-页大小
        request.setPageSize(pageSize);
        //必填-当前页码从1开始计数
        request.setCurrentPage(currentPage);

        //hint 此处可能会抛出异常，注意catch
        QuerySendDetailsResponse querySendDetailsResponse = optAcsClient.get().getAcsResponse(request);
        return querySendDetailsResponse;
    }    
    
//    public static void main(String[] args) {
//    	AliyunSms sms = new AliyunSms();    	
//    	try {
//    		//发送短信
//    		Map<String, String> param = new HashMap<String, String>(2);
//        	param.put("code", "123456");  
//    		
//        	SendSmsResponse response = sms.send("SMS_141205206", "131xxxxxxxx", param);        	
//        	System.out.println("短信接口返回的数据----------------");
//            System.out.println("Code=" + response.getCode());
//            System.out.println("Message=" + response.getMessage());
//            System.out.println("RequestId=" + response.getRequestId());
//            System.out.println("BizId=" + response.getBizId());            
//            
//            //查明细
//            if(response.getCode() != null && response.getCode().equals("OK")) {
//                QuerySendDetailsResponse querySendDetailsResponse = sms.querySendDetails("131xxx1701", response.getBizId(), null, 10, 1);
//                System.out.println("短信明细查询接口返回数据----------------");
//                System.out.println("Code=" + querySendDetailsResponse.getCode());
//                System.out.println("Message=" + querySendDetailsResponse.getMessage());
//                int i = 0;
//                for(QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse.getSmsSendDetailDTOs())
//                {
//                    System.out.println("SmsSendDetailDTO["+i+"]:");
//                    System.out.println("Content=" + smsSendDetailDTO.getContent());
//                    System.out.println("ErrCode=" + smsSendDetailDTO.getErrCode());
//                    System.out.println("OutId=" + smsSendDetailDTO.getOutId());
//                    System.out.println("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
//                    System.out.println("ReceiveDate=" + smsSendDetailDTO.getReceiveDate());
//                    System.out.println("SendDate=" + smsSendDetailDTO.getSendDate());
//                    System.out.println("SendStatus=" + smsSendDetailDTO.getSendStatus());
//                    System.out.println("Template=" + smsSendDetailDTO.getTemplateCode());
//                }
//                System.out.println("TotalCount=" + querySendDetailsResponse.getTotalCount());
//                System.out.println("RequestId=" + querySendDetailsResponse.getRequestId());
//            }
//    	} catch (ClientException e) {
//    		e.printStackTrace();
//    	}        
//    }
}