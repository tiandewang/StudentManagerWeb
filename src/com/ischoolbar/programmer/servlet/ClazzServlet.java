package com.ischoolbar.programmer.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ischoolbar.programmer.dao.ClazzDao;
import com.ischoolbar.programmer.model.Clazz;
import com.ischoolbar.programmer.model.Page;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 
 * @author tian-de-gui-ren 
 * 班级信息管理servlet
 */
public class ClazzServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String method = request.getParameter("method");
		if ("toClazzListView".equals(method)) {
			clazzList(request, response);
		}else if ("getClazzList".equals(method)) {
			getClazzList(request, response);
		}
	}

	private void clazzList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			request.getRequestDispatcher("view/clazzList.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getClazzList(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		Integer currentPage = Integer.parseInt(request.getParameter("page"));
		Integer pageSize = Integer.parseInt(request.getParameter("rows"));
		Clazz clazz = new Clazz();
		clazz.setName(name);
		ClazzDao clazzDao = new ClazzDao();
		List<Clazz> clazzList = clazzDao.getClazzList(clazz, new Page(currentPage, pageSize));
		clazzDao.closecon();
		JsonConfig jsonConfig = new JsonConfig();
		String clazzListString = JSONArray.fromObject(clazzList, jsonConfig).toString();
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(clazzListString);
		} catch (IOException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

} 
