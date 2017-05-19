/**
 * Copyright (c) 2011-2014, hubin (243194995@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.thinkgem.jeesite.modules.sso.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sso.util.PasswordUtils;
import com.thinkgem.jeesite.modules.uc.entity.UcMember;
import com.thinkgem.jeesite.modules.uc.service.UcMemberService;

/**
 * 登录
 */
@Controller
@RequestMapping(value = "${frontPath}/sso/login")
public class SsoLoginController extends BaseController {

	@Autowired
	public UcMemberService memberService;
	/**
	 * 登录 （注解跳过权限验证）
	 */
	@RequestMapping("login")
	public String login(HttpServletRequest request) {
		SSOToken st = SSOHelper.getToken(request);
		System.out.println("执行");
		if (st != null) {
			return "modules/sso/index";
		}
		return "modules/sso/login";
	}

	/**
	 * 登录 （注解跳过权限验证）
	 */
	@RequestMapping("loginpost")
	public String loginpost(HttpServletRequest request,HttpServletResponse response) {
		/**
		 * 生产环境需要过滤sql注入
		 */
		WafRequestWrapper req = new WafRequestWrapper(request);
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		UcMember u = new UcMember();
		u.setUsername(username);
		UcMember user = memberService.getUserByName(u);
		
		if (null != user) {
			if (user.getId()!=null) {
				/*
				 * authSSOCookie 设置 cookie 同时改变 jsessionId
				 */
				SSOToken st = new SSOToken(request);
				st.setId(user.getId());
				st.setUid(user.getId().toString());
				st.setType(1);
				
				//记住密码，设置 cookie 时长 1 周 = 604800 秒 【动态设置 maxAge 实现记住密码功能】
				//String rememberMe = req.getParameter("rememberMe");
				//if ( "on".equals(rememberMe) ) {
				//	request.setAttribute(SSOConfig.SSO_COOKIE_MAXAGE, 604800); 
				//}
				SSOHelper.setSSOCookie(request, response, st, true);
				
				
				System.out.println(HttpUtil.decodeURL(request.getRequestURI()));
				/*
				 * 登录需要跳转登录前页面，自己处理 ReturnURL 使用 
				 * HttpUtil.decodeURL(xx) 解码后重定向
				 */
				return "modules/sso/index";
			}
		}
		
		return "login";
	}
}
