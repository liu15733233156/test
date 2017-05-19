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

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.kisso.SSOConfig;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.SSOToken;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.common.util.HttpUtil;
import com.baomidou.kisso.web.waf.request.WafRequestWrapper;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sso.util.CaptchaUtil;
import com.thinkgem.jeesite.modules.uc.entity.UcMember;
import com.thinkgem.jeesite.modules.uc.service.UcMemberService;

/**
 * 首页
 */
@Controller
@RequestMapping(value = "${adminPath}/sso/index")
public class IndexController extends BaseController {
	@Autowired
	public UcMemberService memberService;
	/**
	 * <p>
	 * SSOHelper.getToken(request)
	 * 
	 * 从 Cookie 解密 token 使用场景，拦截器
	 * </p>
	 * 
	 * <p>
	 * SSOHelper.attrToken(request)
	 * 
	 * 非拦截器使用减少二次解密
	 * </p>
	 */
	@RequestMapping("/index")
	public String index(Model model,HttpServletRequest request,HttpServletResponse response ) {
		SSOToken st = SSOHelper.attrToken(request);
		if (st != null) {
			System.err.println(" Long 类型 ID: " + st.getId());
			model.addAttribute("userId", st.getUid());
		}
		System.err.println(" 启动注入测试模式：" + SSOConfig.getInstance().getRunMode());
		return "index";
	}
	
	/**
	 * 验证码 （注解跳过权限验证）
	 */
	@Login(action = Action.Skip)
	@ResponseBody
	@RequestMapping("/verify")
	public void verify(HttpServletResponse response) {
		try {
			String verifyCode = CaptchaUtil.outputImage(response.getOutputStream());
			System.out.println("验证码:" + verifyCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 异常 404 提示页
	 */
	@RequestMapping("/404")
	public String error_404() {
		return "error/404";
	}
	
	/**
	 * 登录 （注解跳过权限验证）
	 */
	@RequestMapping("loginpost")
	public String loginpost(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("a");
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
