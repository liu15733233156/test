package com.thinkgem.jeesite.modules.sso.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * springmvc拦截器测试
 */
public class TestInterceptor implements HandlerInterceptor {


	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler )
		throws Exception {
		System.out.println(" TestInterceptor preHandle ... ");
		if ( handler instanceof HandlerMethod ) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			if ( handlerMethod.getBean() instanceof HandlerInterceptor ) {
				return ((HandlerInterceptor) handlerMethod.getBean()).preHandle(request, response, handler);
			}
		}
		return true;

	}


	@Override
	public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView ) throws Exception {
		System.out.println(" TestInterceptor postHandle ... ");
		if ( handler instanceof HandlerMethod ) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			if ( handlerMethod.getBean() instanceof HandlerInterceptor ) {
				((HandlerInterceptor) handlerMethod.getBean()).postHandle(request, response, handler, modelAndView);
			}
		}
	}


	@Override
	public void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex )
		throws Exception {
		System.out.println(" TestInterceptor afterCompletion ... ");
		if ( handler instanceof HandlerMethod ) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			if ( handlerMethod.getBean() instanceof HandlerInterceptor ) {
				((HandlerInterceptor) handlerMethod.getBean()).afterCompletion(request, response, handler, ex);
			}

		}
	}
}
