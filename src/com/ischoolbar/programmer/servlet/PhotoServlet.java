package com.ischoolbar.programmer.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
		if("getPhoto".equals(method)) {
			getPhoto(request, response);
		}
	}
	private void getPhoto(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
//		File file = new File();
		String path = request.getSession().getServletContext().getRealPath("");
		File file =new java.io.File(path+"\\WebContent\\file\\Logo.jpg");
		try {
			response.getWriter().write(file.getParent());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
