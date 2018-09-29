package com.ischoolbar.programmer.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ischoolbar.programmer.dao.ClazzDao;
import com.ischoolbar.programmer.model.Clazz;
import com.ischoolbar.programmer.model.Page;

import net.sf.json.JSONObject;

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
		}else if("AddClazz".equals(method)) {
			addClazz(request, response);
		}
	}

	private void addClazz(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String name = request.getParameter("clazzName");
		String info = request.getParameter("info");
		Clazz clazz = new Clazz();
		clazz.setName(name);
		clazz.setInfo(info);
		ClazzDao clazzDao = new ClazzDao();
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
		String name = request.getParameter("clazzName");
		Integer currentPage = Integer.parseInt(request.getParameter("page"));
		Integer pageSize = Integer.parseInt(request.getParameter("rows"));
		Clazz clazz = new Clazz();
		clazz.setName(name);
		ClazzDao clazzDao = new ClazzDao();
		List<Clazz> clazzList = clazzDao.getClazzList(clazz, new Page(currentPage, pageSize));
		int total = clazzDao.getClazzListTotal(clazz);
		clazzDao.closecon();
		response.setCharacterEncoding("UTF-8");
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("total",total);
		ret.put("rows", clazzList);
		try {
			response.getWriter().write(JSONObject.fromObject(ret).toString());
		} catch (IOException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

} 
