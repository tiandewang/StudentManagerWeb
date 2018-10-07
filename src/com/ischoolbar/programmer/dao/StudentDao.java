package com.ischoolbar.programmer.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ischoolbar.programmer.model.Student;

public class StudentDao extends BaseDao {
	public boolean addStudent(Student student) {
		String sql = "insert into s_student values(null,'"+student.getSn()+"','"+student.getName()+"'";
		sql += ",'" + student.getPassword() + "'," + student.getClazzId();
		sql += ",'" + student.getSex() + "','" + student.getMobile() + "'";
		sql += ",'" + student.getQq() + "',null)";
		return update(sql);
	}
	public Student getStudent(int id) {
		String sql = "select * form s_student where id = " + id;
		Student student = null;
		ResultSet resultSet = query(sql);
		try {
			if(resultSet.next()) {
				student = new Student();
				student.setId(resultSet.getInt("id"));
				student.setClazzId(resultSet.getInt("clazz_id"));
				student.setMobile(resultSet.getString("mobile"));
				student.setName(resultSet.getString("name"));
				student.setPassword(resultSet.getString("password"));
				student.setQq(resultSet.getString("qq"));
				student.setSex(resultSet.getString("sex"));
				student.setSn(resultSet.getString("sn"));
				return student;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student;
	}
}
