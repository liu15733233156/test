package com.wenpu.user.spring.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 用户信息参数转换器
 */
public class LoginInfoArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public LoginInfo resolveArgument( MethodParameter parameter, ModelAndViewContainer viewContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory ) throws Exception {
		//此处获取登录信息
		return new LoginInfo(1L, "xiaoming");
	}


	@Override
	public boolean supportsParameter( MethodParameter parameter ) {
		return parameter.getParameterType().equals(LoginInfo.class);
	}

}
