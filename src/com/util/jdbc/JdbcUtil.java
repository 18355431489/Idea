package com.util.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * JDBC工具类
 * @author 唐小甫
 * @datetime 2020-12-05 23:17:20
 */
public class JdbcUtil {

    /** 连接数据库服务 */
    private static final String DRIVER_CLASSNAME = "com.mysql.jdbc.Driver";
    /** 数据库连接地址 */
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/tem";
    /** 数据库用户名 */
    private static final String USERNAME = "root";
    /** 数据库密码 */
    private static final String PASSWORD = "1234";

    private static ThreadLocal<Connection> threadLocal;

    
    /**
     * 配置信息初始化
     * @author 唐小甫
     * @datetime 2020-12-05 23:17:20
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
     * 获取连接
     * @return Connection
     * @throws SQLException
     * @author 唐小甫
     * @datetime 2020-12-05 23:18:32
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = threadLocal.get();
        if (conn == null) {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            threadLocal.set(conn);
        }
        return conn;
    }

    
    /**
     * 开启事务：事务关闭自动提交
     * @throws SQLException
     * @author 唐小甫
     * @datetime 2020-12-05 23:09:41
     */
    public static void beginTransaction() throws SQLException {
        getConnection().setAutoCommit(false);
    }


    /**
     * 事务提交
     * @throws SQLException
     * @author 唐小甫
     * @datetime 2020-12-05 23:10:22
     */
    public static void commit() throws SQLException {
        getConnection().commit();
    }


    /**
     * 事务回滚
     * @throws SQLException
     * @author 唐小甫
     * @datetime 2020-12-05 23:10:46
     */
    public static void roolback() throws SQLException {
        getConnection().rollback();
    }


    /**
     * 关闭连接
     * @throws SQLException
     * @author 唐小甫
     * @datetime 2020-12-05 23:11:57
     */
    public static void close() throws SQLException {
        getConnection().close();
        threadLocal.remove();
    }
}