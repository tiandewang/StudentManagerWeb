package com.ischoolbar.programmer.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * 
 * @author tian-de-gui-ren 
 * 数据库连接util
 */
public class DbUtil {

	private String dbUrl = "jdbc:mysql://localhost:3306/db_student_manager_web?useUnicod=ture&characterEncoding=utf-8";
	private String dbUser = "root";
	private String dbPassword = "";
	private String jdbcName = "com.mysql.jdbc.Driver";
	private Connection connection = null;

	public Connection getConnection() throws SQLException {
		try {
			Class.forName(jdbcName);
			connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			System.out.println("数据库连接成功");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}
		return connection;
	}
	public void closeCon() {
		if(connection !=null)
			try {
				connection.close();
				System.out.println("数据库连接已关闭");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		DbUtil dbUtil=new DbUtil();
		dbUtil.getConnection();
	}

}
