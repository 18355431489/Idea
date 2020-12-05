package com.util.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Druid数据库工具类
 * @author 唐小甫
 * @datetime 2020-12-05 23:04:02
 */
public class DruidUtil {

    /** Druid数据源 */
    private static DruidDataSource dataSource = null;

    /** 数据库连接对象 */
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    
    /**
     * 初始化Druid连接池
     * @author 唐小甫
     * @datetime 2020-12-05 23:04:40
     */
    static {
        try {
            // 编译后从项目的bin目录开始向下查找，以/为目录分割
            InputStream is = DruidUtil.class.getClassLoader().getResourceAsStream("com/util/jdbc/db.properties");
            Properties properties = new Properties();
            properties.load(is);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("初始化连接池失败....");
        }
    }


    /**
     * 获取数据源
     * @return DataSource
     * @author 唐小甫
     * @datetime 2020-12-05 23:06:24
     */
    public static DataSource getDataSource() {
        return dataSource;
    }


    /**
     * 获取连接
     * @return Connection
     * @throws SQLException
     * @author 唐小甫
     * @datetime 2020-12-05 23:06:50
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = threadLocal.get();
        if (conn == null) {
            conn = dataSource.getConnection();
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