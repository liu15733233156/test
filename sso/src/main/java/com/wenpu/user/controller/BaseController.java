package com.wenpu.user.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mybatis.pagination.Pagination;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wenpu.user.mail.MailHelper;


public class BaseController implements HandlerInterceptor {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected MailHelper mail;
	@Autowired
	protected HttpServletRequest request;
	
	@Autowired
	protected HttpServletResponse response;

	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler )
		throws Exception {
		System.out.println(" BaseController preHandle ... ");
		return true;
	}


	@Override
	public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView ) throws Exception {
		System.out.println(" BaseController postHandle ... ");
		if ( isLegalView(modelAndView) ) {
			modelAndView.addObject("newdate", new Date());
		}
	}


	@Override
	public void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex )
		throws Exception {
		System.out.println(" BaseController afterCompletion ... ");
	}
	
	/**
	 * 获取分页对象（total为0 自动查询分页总数）
	 * 
	 * @param request
	 * @return
	 */
	protected Pagination getPagination(HttpServletRequest request) {
		return getPagination(request, 0);
	}

	/**
	 * 获取分页对象
	 * 
	 * @param request
	 * @param total
	 *            自定义查询总数
	 * @return
	 */
	protected Pagination getPagination(HttpServletRequest request, int total) {
		int _current = 1;
		int _size = 15;

		/** 当前页 */
		String pageCurrent = request.getParameter("pageCurrent");
		if (StringUtils.isNotEmpty(pageCurrent)) {
			_current = Integer.valueOf(pageCurrent);
		}

		/** 每页显示记录条数 */
		String pageSize = request.getParameter("pageSize");
		if (StringUtils.isNotEmpty(pageSize)) {
			_size = Integer.valueOf(pageSize);
		}

		return new Pagination(_current, _size, total);
	}


	/**
	 * 判断是否为合法的视图地址
	 * <p>
	 * 
	 * @param modelAndView
	 *            spring 视图对象
	 * @return boolean
	 */
	protected boolean isLegalView( ModelAndView modelAndView ) {
		boolean legal = false;
		if ( modelAndView != null ) {
			String viewUrl = modelAndView.getViewName();
			if ( viewUrl != null && viewUrl.contains("redirect:") ) {
				legal = false;
			} else {
				legal = true;
			}
		}
		return legal;
	}
	
	protected String redirectTo( String url ) {
		StringBuffer rto = new StringBuffer("redirect:");
		rto.append(url);
		return rto.toString();
	}
}
