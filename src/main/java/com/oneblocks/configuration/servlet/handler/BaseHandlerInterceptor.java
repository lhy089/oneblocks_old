package com.oneblocks.configuration.servlet.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.oneblocks.configuration.RequestConfiguration;
import com.oneblocks.configuration.exception.BaseException;
import com.oneblocks.configuration.http.BaseResponseCode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BaseHandlerInterceptor implements HandlerInterceptor{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	private static final String LOGIN = "loginMemberInfo";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("preHandle requestURI : {}", request.getRequestURI());
		if(handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			RequestConfiguration requestConfig = handlerMethod.getMethodAnnotation(RequestConfiguration.class);
			if(requestConfig != null) {
				if(requestConfig.loginCheck()) {
					if (request.getSession().getAttribute(LOGIN) == null) {
						throw new BaseException(BaseResponseCode.LOGIN_REQUIRED);
					}
				}
			}
		}
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object Handler, ModelAndView modelAndView) throws Exception {
		logger.info("postHandle requestURI : {}", request.getRequestURI());
	}

	
	
}
