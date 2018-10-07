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
import com.ischoolbar.programmer.dao.StudentDao;
import com.ischoolbar.programmer.model.Clazz;
import com.ischoolbar.programmer.model.Page;
import com.ischoolbar.programmer.model.Student;
import com.ischoolbar.programmer.util.SnGenerateUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 
 * @author tian-de-gui-ren
 *学生信息管理功能实现servlet
 */
public class StudentServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String method = request.getParameter("method");
		if("toStudentListView".equals(method)) {
			studentList(request, response);
		}else if("AddStudent".equals(method)) {
			addStudent(request, response);
		}else if("StudentList".equals(method)) {
			getStudentList(request, response);
		}
	}
	private void getStudentList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String name = request.getParameter("studentName");
		Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		Integer pageSize = request.getParameter("rows") == null ? 999 :Integer.parseInt(request.getParameter("rows"));
		Integer clazz = request.getParameter("clazzid") == null ? 0 :Integer.parseInt(request.getParameter("clazzid"));
		Student student = new Student();
		student.setName(name);
		student.setClazzId(clazz);
		StudentDao studentDao = new StudentDao();
		List<Student> studentList = studentDao.getStudentList(student, new Page(currentPage, pageSize));
		int total = studentDao.getStudentListTotal(student);
		studentDao.closeCon();
		response.setCharacterEncoding("UTF-8");
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("total",total);
		ret.put("rows", studentList);
		try {
			String from = request.getParameter("from");
			if("combox".equals(from)) {
				response.getWriter().write(JSONArray.fromObject(studentList).toString());
			}else {
			response.getWriter().write(JSONObject.fromObject(ret).toString());
			}
		} catch (IOException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		String mobile = request.getParameter("mobile");
		String qq = request.getParameter("qq");
		int clazzId = Integer.parseInt(request.getParameter("clazzid"));
		Student student = new Student();
		student.setClazzId(clazzId);
		student.setMobile(mobile);
		student.setName(name);
		student.setPassword(password);
		student.setQq(qq);
		student.setSex(sex);
		student.setSn(SnGenerateUtil.generateSn(clazzId));
		StudentDao studentDao = new StudentDao();
		if(studentDao.addStudent(student)) {
			try {
				response.getWriter().write("success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				studentDao.closeCon();
			}
		}
	}

	private void studentList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		try {
			request.getRequestDispatcher("view/studentList.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
}
