package com.util;

import java.sql.SQLException;
import com.util.jdbc.JdbcUtil;
import com.util.jdbc.QueryRunnerDBUtil;

/**
 * 测试类
 * 
 * @author 唐小甫
 * @datetime: 2020-12-06 12:02:52
 */
public class Demo {
    public static void main(String[] args) {
        String sql = null;
        try {
            sql = "insert into tb_user(username, money) values(?, ?)";
            Long id = QueryRunnerDBUtil.executeSave(sql, "刘德华", 99556699);
            System.out.println(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JdbcUtil.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}