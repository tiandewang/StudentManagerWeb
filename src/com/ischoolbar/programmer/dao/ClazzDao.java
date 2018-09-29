package com.ischoolbar.programmer.dao;
/**
 * 
 * @author tian-de-gui-ren
 *班级信息数据库操作
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ischoolbar.programmer.model.Clazz;
import com.ischoolbar.programmer.model.Page;
import com.ischoolbar.programmer.util.StringUtil;

public class ClazzDao extends BaseDao {
	public List<Clazz> getClazzList(Clazz clazz, Page page) {
		List<Clazz> ret = new ArrayList<Clazz>();
		String sql = "select * from s_clazz ";
		if (!StringUtil.isEmpty(clazz.getName())) {
			sql += "where name like '%" + clazz.getName() + "%'";
		}
		sql += " limit " + page.getStart() + "," + page.getPageSize();
		ResultSet resultSet = query(sql);
		try {
			while (resultSet.next()) {
				Clazz cl = new Clazz();
				cl.setId(resultSet.getInt("id"));
				cl.setName(resultSet.getString("name"));
				cl.setInfo(resultSet.getString("info"));
				ret.add(cl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	public int getClazzListTotal(Clazz clazz) {
		int total = 0;
		String sql = "select count(*) as total from s_clazz ";
		if (!StringUtil.isEmpty(clazz.getName())) {
			sql += "where name like '%" + clazz.getName() + "%'";
		}
		ResultSet resultSet = query(sql);
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
	public boolean addClazz(Clazz clazz) {
		String sql = "insert into s_clazz values(null,'"+clazz.getName()+"','"+clazz.getInfo()+"')";
		System.out.println(sql);
		return update(sql);
	}
}
