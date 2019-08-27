package com.jinyafu.thirdpart.service.system;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.jinyafu.jmall.entity.third.system.User;
import com.jinyafu.jmall.mapper.third.system.UserMapper;
import com.jinyafu.thirdpart.common.code.MessageOutput;
import com.jinyafu.thirdpart.common.code.OutputCode;

@Service
public class UserService {
	
	@Resource
	UserMapper userMapper;
	
	public Object login(String account, String password) {
		Map<String,Object> map=new HashMap<>();
		try {
			if (StringUtils.isNotBlank(account) && StringUtils.isNotBlank(password)) {
				User user = userMapper.getByAccount(account);
				if (null != user) {
					if (password.equals(user.getPassword())) {
						map.put("user", user);
					} else {
						return  MessageOutput.get(OutputCode.PX.getCode(), OutputCode.PX.getMessage());
					}
				} else {
					return  MessageOutput.get(OutputCode.ACCOUNT_ISNOT_EXIST.getCode(), OutputCode.ACCOUNT_ISNOT_EXIST.getMessage());
				}
			} else {
				return  MessageOutput.get(OutputCode.PARAMS_INVALID_EMPTY.getCode(), OutputCode.PARAMS_INVALID_EMPTY.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			MessageOutput.get(OutputCode.EX.getCode(), OutputCode.EX.getMessage());
		}
		return MessageOutput.ok(map);
	}

}
