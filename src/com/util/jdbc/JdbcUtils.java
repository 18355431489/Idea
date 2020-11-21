package com.util.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author: 唐小甫
 * @describe: JDBC工具
 * @createTime: 2020-05-14 12:42:38
 * @version: 1.0
 */
public class JdbcUtils {

    private static final String DRIVER_CLASSNAME = "com.mysql.jdbc.Driver";
    private static final String JDBCURL = "jdbc:mysql://localhost:3306/storehouse";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";

    private static ThreadLocal<Connection> threadLocal;

    
    /**
     * @describe: 配置信息初始化
     */
    static {
        try {
            Class.forName(DRIVER_CLASSNAME);
            threadLocal = new ThreadLocal<Connection>();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    
    /**
     * @describe: 获取连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = threadLocal.get();
        if (conn == null) {
            conn = DriverManager.getConnection(JDBCURL, USERNAME, PASSWORD);
            threadLocal.set(conn);
        }
        return conn;
    }

    
    /**
     * @describe: 关闭连接
     * @throws SQLException
     */
    public static void closeConnection() throws SQLException {
        getConnection().close();
        threadLocal.remove();
    }

    
    /**
     * @describe: 设置自动提交事务 true/false
     * @param autoCommit
     * @throws SQLException
     */
    public static void setAutoCommit(Boolean autoCommit) throws SQLException {
        getConnection().setAutoCommit(autoCommit);
    }

    
    /**
     * @describe: 事务回滚
     * @throws SQLException
     */
    public static void rollBack() throws SQLException {
        getConnection().rollback();
    }

    
    /**
     * @describe: 事务提交
     * @throws SQLException
     */
    public static void commit() throws SQLException {
        getConnection().commit();
    }
}