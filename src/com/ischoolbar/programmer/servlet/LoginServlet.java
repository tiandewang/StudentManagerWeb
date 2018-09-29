package com.ischoolbar.programmer.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ischoolbar.programmer.dao.AdminDao;
import com.ischoolbar.programmer.model.Admin;
import com.ischoolbar.programmer.util.StringUtil;

/**
 * 
 * @author tian-de-gui-ren 登录验证servlet
 */
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5870852067427524781L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String vcode = request.getParameter("vcode");
		String name = request.getParameter("account");
		String password = request.getParameter("password");
		int type = Integer.parseInt(request.getParameter("type"));
		String logincaptcha = request.getSession().getAttribute("loginCaptcha").toString();
		if (StringUtil.isEmpty(vcode)) {
			response.getWriter().write("vcodeError");
			return;
		}
		if (!vcode.toUpperCase().equals(logincaptcha.toUpperCase())) {
			response.getWriter().write("vcodeError");
			return;
		}
		// 验证码验证通过，对比用户名密码是否正确
		switch (type) {
		case 1: {
			AdminDao adminDao = new AdminDao();
			Admin admin = adminDao.login(name, password);
			adminDao.closecon();
			if (admin == null) {
				response.getWriter().write("loginError");
				return;
			}
			HttpSession session = request.getSession();
			session.setAttribute("user", admin);
			session.setAttribute("userType", type);
			response.getWriter().write("admin");
			break;
		}
		default:
			break;
		}
		// 用户名密码正确
	}
}
