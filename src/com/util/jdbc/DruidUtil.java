package com.util.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
  * @author 唐小甫
  * @datetime 2020/11/21 13:10
 */
public class DruidUtil {

    private static DruidDataSource dataSource = null;

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    static {
        //创建流
        try {
            InputStream is = DruidUtil.class.getClassLoader().getResourceAsStream("db.properties");
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
     * @return
     */
    public static DataSource getDataSource() {
        return dataSource;
    }


    /**
     * 获取连接
     * @return
     * @throws SQLException
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
     * 开启事务
     * @throws SQLException
     */
    public static void beginTransaction() throws SQLException {
        getConnection().setAutoCommit(false);
    }


    /**
     * 提交事务: 与回滚事务互斥
     * @throws SQLException
     */
    public static void commit() throws SQLException {
        getConnection().commit();
    }


    /**
     * 回滚事务: 与提交事务互斥
     * @throws SQLException
     */
    public static void roolback() throws SQLException {
        getConnection().rollback();
    }


    /**
     * 关闭连接
     * @throws SQLException
     */
    public static void close() throws SQLException {
        getConnection().close();
        threadLocal.remove();
    }
}