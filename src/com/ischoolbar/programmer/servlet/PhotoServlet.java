package com.ischoolbar.programmer.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;

import com.ischoolbar.programmer.dao.StudentDao;
import com.ischoolbar.programmer.model.Student;
import com.lizhou.exception.FileFormatException;
import com.lizhou.exception.NullFileException;
import com.lizhou.exception.ProtocolException;
import com.lizhou.exception.SizeException;
import com.lizhou.fileload.FileUpload;

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
		}else if ("SetPhoto".equals(method)) {
			UploadPhoto(request, response);
		}
	}
	private void UploadPhoto(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int sid = request.getParameter("sid") == null ? 0 : Integer.parseInt(request.getParameter("sid"));
		int tid = request.getParameter("tid") == null ? 0 : Integer.parseInt(request.getParameter("tid"));
		FileUpload fileUpload = new FileUpload(request);
		fileUpload.setFileFormat("jpg");
		fileUpload.setFileFormat("png");
		fileUpload.setFileFormat("pneg");
		fileUpload.setFileFormat("gif");
		fileUpload.setFileSize(2048*1000);
			try {
				InputStream uploadInputStream = fileUpload.getUploadInputStream();
				if(sid != 0) {
					Student student = new Student();
					student.setId(sid);
					student.setPhoto(uploadInputStream);
					StudentDao studentDao = new StudentDao();
					try {
						if(studentDao.setStudentPhoto(student)) {
							response.getWriter().write("<div id = 'message'>上传成功</div>");
						}else {
							response.getWriter().write("<div id = 'message'>上传失败</div>");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	private void getPhoto(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		// File file = new File();
		int sid = request.getParameter("sid") == null ? 0 : Integer.parseInt(request.getParameter("sid"));
		int tid = request.getParameter("tid") == null ? 0 : Integer.parseInt(request.getParameter("tid"));
		if (sid != 0) {
			//学生
				StudentDao studentDao = new StudentDao();
				Student student = studentDao.getStudent(sid);
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
