package com.ischoolbar.programmer.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ischoolbar.programmer.dao.StudentDao;
import com.ischoolbar.programmer.model.Student;

/**
 * 
 * @author tian-de-gui-ren 
 * 图片处理servlet
 */
public class PhotoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3274927179113071465L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String method = request.getParameter("method");
		if ("getPhoto".equals(method)) {
			getPhoto(request, response);
		}
	}
	private void getPhoto(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		// File file = new File();
		int id = request.getParameter("uid") == null ? 0 : Integer.parseInt(request.getParameter("uid"));
		if (id != 0) {
			int useType = Integer.parseInt(request.getParameter("useType").toString());
			//学生
			if (useType == 2) {
				StudentDao studentDao = new StudentDao();
				Student student = studentDao.getStudent(id);
				studentDao.closeCon();
				if(student != null) {
					InputStream photo = student.getPhoto();
					if (photo != null) {
						try {
							byte[] b = new byte[photo.available()];
							photo.read(b);
							response.getOutputStream().write(b, 0, b.length);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return;
					}
				}
			}else if (useType == 3) {
				//老师
				return;
			}
		}
		String path = request.getSession().getServletContext().getRealPath("");
		File file = new java.io.File(path + "\\file\\Logo.jpg");
		try {
			FileInputStream fis = new FileInputStream(file);
			byte[] b = new byte[fis.available()];
			fis.read(b);
			response.getOutputStream().write(b, 0, b.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
