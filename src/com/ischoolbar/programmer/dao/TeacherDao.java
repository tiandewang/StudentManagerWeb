package com.ischoolbar.programmer.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ischoolbar.programmer.model.Page;
import com.ischoolbar.programmer.model.Student;
import com.ischoolbar.programmer.model.Teacher;
import com.ischoolbar.programmer.util.StringUtil;

/**
 * 
 * @author tian-de-gui-ren
 *教师表数据库操作
 */
public class TeacherDao extends BaseDao {
	public boolean addTeacher(Teacher teacher) {
		String sql = "insert into s_teacher values(null,'" + teacher.getSn() + "','" + teacher.getName() + "'";
		sql += ",'" + teacher.getPassword() + "'," + teacher.getClazzId();
		sql += ",'" + teacher.getSex() + "','" + teacher.getMobile() + "'";
		sql += ",'" + teacher.getQq() + "',null)";
		return update(sql);
	}
	public boolean editTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		String sql = "update s_teacher set name = '"+teacher.getName()+"'";
		sql += ",sex = '" + teacher.getSex() + "'";
		sql += ",mobile = '" + teacher.getMobile() + "'";
		sql += ",qq = '" + teacher.getQq() + "'";
		sql += ",clazz_id = " + teacher.getClazzId();
		sql += " where id = " + teacher.getId();
		return update(sql);
	}
	public List<Teacher> getTeacherList(Teacher teacher, Page page) {
		List<Teacher> ret = new ArrayList<Teacher>();
		String sql = "select * from s_teacher ";
		if (!StringUtil.isEmpty(teacher.getName())) {
			sql += "and name like '%" + teacher.getName() + "%'";
		}
		if (teacher.getClazzId() != 0) {
			sql += " and clazz_id = " + teacher.getClazzId();
		}
		if (teacher.getId() != 0) {
			sql += " and id = " + teacher.getId();
		}
		sql += " limit " + page.getStart() + "," + page.getPageSize();
		ResultSet resultSet = query(sql.replaceFirst("and", "where"));
		try {
			while (resultSet.next()) {
				Teacher t = new Teacher();
				t.setId(resultSet.getInt("id"));
				t.setClazzId(resultSet.getInt("clazz_id"));
				t.setMobile(resultSet.getString("mobile"));
				t.setName(resultSet.getString("name"));
				t.setPassword(resultSet.getString("password"));
				t.setPhoto(resultSet.getBinaryStream("photo"));
				t.setQq(resultSet.getString("qq"));
				t.setSex(resultSet.getString("sex"));
				t.setSn(resultSet.getString("sn"));
				ret.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	public int getTeacherListTotal(Teacher teacher) {
		int total = 0;
		String sql = "select count(*) as total from s_teacher ";
		if (!StringUtil.isEmpty(teacher.getName())) {
			sql += "and name like '%" + teacher.getName() + "%'";
		}
		if (teacher.getClazzId() != 0) {
			sql += " and clazz_id = " + teacher.getClazzId();
		}
		if(teacher.getId() != 0){
			sql += " and id = " + teacher.getId();
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
