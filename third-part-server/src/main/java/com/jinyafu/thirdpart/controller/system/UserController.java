package com.jinyafu.thirdpart.controller.system;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jinyafu.thirdpart.service.system.UserService;

@RestController
@RequestMapping("/third/system")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/index/login")
	public Object login(
			HttpServletRequest request,
			@RequestBody Map<String,Object> map) {
		return userService.login(map.get("account").toString(), map.get("password").toString());
	}

}
