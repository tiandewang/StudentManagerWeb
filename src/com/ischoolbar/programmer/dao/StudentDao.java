package com.ischoolbar.programmer.dao;

import com.ischoolbar.programmer.model.Student;

public class StudentDao extends BaseDao {
	public boolean addStudent(Student student) {
		String sql = "insert into s_student values(null,'" + student.getSn() + "','" + student.getName() + "'";
		sql += ",'" + student.getPassword() + "'," + student.getClazzId();
		sql += ",'" + student.getSex() + "','" + student.getMobile() + "'";
		sql += ",'" + student.getQq() + "')";
		return update(sql);
	}
}
