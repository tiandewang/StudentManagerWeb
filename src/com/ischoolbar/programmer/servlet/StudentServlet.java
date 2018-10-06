package com.ischoolbar.programmer.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ischoolbar.programmer.dao.StudentDao;
import com.ischoolbar.programmer.model.Student;
import com.ischoolbar.programmer.util.SnGenerateUtil;
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
