package com.ischoolbar.programmer.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * @author tian-de-gui-ren
 *教师信息管理servlet类
 */
public class TeacherServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8421947373714720118L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String method = request.getParameter("method");
		if("toTeacherListView".equals(method)) {
			TeacherList(request, response);
		}else if("AddStudent".equals(method)) {
			addTeacher(request, response);
		}else if("StudentList".equals(method)) {
			getTeacherList(request, response);
		}else if("EditStudent".equals(method)) {
			editTeachert(request, response);
		}else if ("DeleteStudent".equals(method)) {
			deleteTeacher(request, response);
		}
	}

	private void deleteTeacher(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void editTeachert(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void getTeacherList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void addTeacher(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void TeacherList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			request.getRequestDispatcher("view/teacherList.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
