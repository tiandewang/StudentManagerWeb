package com.ischoolbar.programmer.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ischoolbar.programmer.model.Clazz;
import com.ischoolbar.programmer.model.Page;
import com.ischoolbar.programmer.model.Student;
import com.ischoolbar.programmer.util.StringUtil;

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
	public List<Student> getStudentList(Student student, Page page) {
		List<Student> ret = new ArrayList<Student>();
		String sql = "select * from s_student ";
		if (!StringUtil.isEmpty(student.getName())) {
			sql += "where name like '%" + student.getName() + "%'";
		}
		if(student.getClazzId() != 0) {
			sql += " and clazz_id = " + student.getClazzId();
		}
		sql += " limit " + page.getStart() + "," + page.getPageSize();
		ResultSet resultSet = query(sql.replaceFirst("and", "where"));
		try {
			while (resultSet.next()) {
				Student s = new Student();
				s.setId(resultSet.getInt("id"));
				s.setClazzId(resultSet.getInt("clazz_id"));
				s.setMobile(resultSet.getString("mobile"));
				s.setName(resultSet.getString("name"));
				s.setPassword(resultSet.getString("password"));
				s.setQq(resultSet.getString("qq"));
				s.setSex(resultSet.getString("sex"));
				s.setSn(resultSet.getString("sn"));
				ret.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	public int getStudentListTotal(Student student) {
		int total = 0;
		String sql = "select count(*) as total from s_student ";
		if (!StringUtil.isEmpty(student.getName())) {
			sql += "where name like '%" + student.getName() + "%'";
		}
		if(student.getClazzId() != 0) {
			sql += " and clazz_id = " + student.getClazzId();
		}
		ResultSet resultSet = query(sql.replaceFirst("and", "where"));
		try {
			while (resultSet.next()) {
				total = resultSet.getInt("total");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}
}
