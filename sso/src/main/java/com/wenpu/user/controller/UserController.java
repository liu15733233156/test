package com.wenpu.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wenpu.user.entity.User;
import com.wenpu.user.service.UserService;
import com.wenpu.user.spring.SpringContextHolder;
import com.wenpu.user.spring.resolver.LoginInfo;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Resource(name = "userServiceImpl")
	private UserService userService;

	
	private LoginInfo injectLoginInfo;


	/**
	 * 查询用户列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list( HttpServletRequest request, Model model ) {
		logger.info(" user/list .. ");
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("abc", "123");
		conditions.put("def", 789);
		List<User> user = userService.getUserList(getPagination(request), conditions);
		if ( user != null && user.size() >= 1 ) {
			/*
			 * 测试取第一条记录
			 */
			for ( int i = 0 ; i < user.size() ; i++ ) {
				System.out.println(" user name: " + user.get(i).getName());
			}

			model.addAttribute("param", user.get(0).getName());
		}
		return "testpage";
	}


	/**
	 * 注解注入方式
	 * 调用 UserService
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test( Model model ) {
		logger.info(" user/test .. ");
		User user = userService.getUserById(1L);
		if ( user != null ) {
			model.addAttribute("param", user.getName());
		}
		return "testpage";
	}


	/**
	 * getBean 注入方式
	 * 调用 UserService
	 */
	@RequestMapping(value = "/bean", method = RequestMethod.GET)
	public String bean( Model model ) {
		logger.info(" user/bean .. ");
		User user = SpringContextHolder.getBean(UserService.class).getUserById(1L);
		if ( user != null ) {
			model.addAttribute("param", user.getName());
		}
		return "testpage";
	}


	/**
	 * 自定义参数转换器测试
	 * 允许 get post 方法
	 */
	@RequestMapping("/resolver")
	public String resolver( Model model, LoginInfo loginIfo ) {
		logger.info(" user/resolver .. ");
		model.addAttribute("param", loginIfo.getUserName());
		return "testpage";
	}


	/**
	 * spring注入 new LoginInfo
	 * 允许 get post 方法
	 */
	@RequestMapping("/inject")
	public String inject( Model model ) {
		logger.info(" user/resolver .. ");
		model.addAttribute("param", injectLoginInfo.getUserName());
		return "testpage";
	}
}
