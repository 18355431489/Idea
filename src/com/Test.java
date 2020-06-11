package com;

import com.pojo.User;
import com.util.DBUtil;

import java.util.List;

public class Test {

	public static void main(String[] args) {
		String sql = "select * from sys_user where user_id = ?;";
		User user = DBUtil.executeQueryObject(sql, User.class, 10);
		System.out.println(user);
		System.out.println("-------------------------------");
		System.out.println("-------------------------------");
		sql = "select * from sys_user";
		List<User> list = DBUtil.executeQueryListObject(sql, User.class);
		list.forEach(System.out::println);
		System.out.println("hello Test Web");
	}
}
