package com.jinyafu.thirdpart.common.interceptor;

import com.jinyafu.thirdpart.common.annotation.PermissionMapping;
import com.jinyafu.thirdpart.common.data.ResultHead;
import com.jinyafu.thirdpart.common.util.common.MenuUtil;
import com.jinyafu.thirdpart.config.AuthManager;
import com.jinyafu.thirdpart.config.ResultMessage;
import com.onlyxiahui.common.lib.util.OnlyJsonUtil;
import com.onlyxiahui.common.util.OnlyStringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class SessionInterceptor implements HandlerInterceptor {

	protected final Logger logger = LogManager.getLogger(this.getClass());

	@Resource
	AuthManager authManager;

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String basePath = request.getContextPath();
		HttpSession session = request.getSession();
		basePath = (null == basePath || "".equals(basePath)) ? "/" : basePath;
		session.setAttribute("basePath", basePath);

		String servletPath = request.getServletPath();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String token = request.getHeader("X-Token");
		boolean isAuth = authManager.isAuth(token);
		boolean hasPermission = hasPermission(token, handler);

		boolean mark = hasPermission;

		if (!hasPermission) {
			ResultHead head = new ResultHead();
			if (!isAuth) {
				head.setResultCode("101");
				head.setResultMessage("登录超时！");
			} else if (!hasPermission) {
				head.setResultCode("102");
				head.setResultMessage("无权访问！");
			} else {
				head.setResultCode("101");
				head.setResultMessage("登录超时！");
			}

			ResultMessage rm = new ResultMessage();
			rm.setHead(head);

			putAllowOrigin(response);
			writer(response, rm);
		}
		if (logger.isInfoEnabled()) {
			logger.info("request url: " + servletPath);
		}
		return mark;
	}

	private void putAllowOrigin(HttpServletResponse response) {
		// 跨域
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		// 跨域 Header
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Content-Type,XFILENAME,XFILECATEGORY,XFILESIZE,X-Token");

	}

	private void writer(HttpServletResponse response, ResultMessage rm) {

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.println(OnlyJsonUtil.objectToJson(rm));
			writer.flush();
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			if (null != writer) {
				writer.close();
			}
		}
	}

	/**
	 * 判断请求来源同步还是异步. 异步请求返回true； 同步请求返回false
	 *
	 * @param request
	 * @return
	 */
	public boolean isSysnc(HttpServletRequest request) {
		String requestType = request.getHeader("X-Requested-With");
		boolean isAjax = (null != requestType && "XMLHttpRequest".equals(requestType));
		return isAjax;
	}

	public boolean hasPermission(String token, Object handler) {
		boolean hasPermission = true;
		if (handler instanceof HandlerMethod) {
			HandlerMethod hm = (HandlerMethod) handler;
			Method method = hm.getMethod();

			PermissionMapping mm = method.getAnnotation(PermissionMapping.class);
			if (null != mm) {
				boolean isIntercept = false;//mm.isIntercept();
				if (isIntercept) {
					String permissionKey = mm.key();
					if (OnlyStringUtil.isNotBlank(permissionKey)) {
						permissionKey = MenuUtil.getKey(permissionKey, ".");
					} else {
						String basePath = "";
						String nodePath = "";

						Object bean = hm.getBean();
						RequestMapping brm = bean.getClass().getAnnotation(RequestMapping.class);

						if (null != brm) {
							String[] paths = brm.value() == null ? brm.path() : brm.value();
							basePath = (null != paths && paths.length > 0) ? paths[0] : "";
						}
						RequestMapping nrm = method.getAnnotation(RequestMapping.class);
						if (null != nrm) {
							String[] paths = nrm.value() == null ? nrm.path() : nrm.value();
							nodePath = (null != paths && paths.length > 0) ? paths[0] : "";
						}
						String id = basePath + nodePath;
						permissionKey = MenuUtil.getKey(id, ".");
					}
					hasPermission = authManager.hasPermission(token, permissionKey);
				}
			}
		}
		return hasPermission;
	}
}
