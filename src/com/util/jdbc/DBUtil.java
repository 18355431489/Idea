package com.util.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: 唐小甫
 * @describe: 数据库操作工具
 * @createTime: 2020-05-14 14:07:51
 * @version: 1.0
 */
public class DBUtil {

    private static PreparedStatement pstmt;
    private static ResultSet set;

    /**
     * @describe: 查询操作，基本数据
     * @param sql
     * @param params
     * @return
     */
    public static int executeUpdate(String sql, Object... params) {
        try {
            beforeOption(sql, params);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            close();
        }
    }


    /**
     * @describe: 查询操作，基本数据
     * @param <T>
     * @param sql
     * @param params
     * @return
     */
    public static <T> T executeQuerySimple(String sql, Object... params) {
        try {
            beforeOption(sql, params);
            ResultSet set = pstmt.executeQuery();
			T instance = null;
            if (set.next()) {
                instance = MapperUtil.rowMapperSimple(set);
            }
			return instance;
        } catch (SQLException e) {
            e.printStackTrace();
			return null;
        } finally {
            close();
        }
    }

    
    /**
     * @describe: 查询操作，基本数据集合
     * @param <T>
     * @param sql
     * @param params
     * @return
     */
    public static <T> List<T> executeQueryListSimple(String sql, Object... params) {
        try {
            beforeOption(sql, params);
            ResultSet set = pstmt.executeQuery();
            List<T> list = new ArrayList<T>();
            while (set.next()) {
                list.add(MapperUtil.rowMapperSimple(set));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            close();
        }
    }

    
    /**
     * @describe: 查询操作，封装到自定义对象
     * @param <T>
     * @param sql
     * @param clazz
     * @param params
     * @return
     */
    public static <T> T executeQueryObject(String sql, Class<T> clazz, Object... params) {
        try {
            beforeOption(sql, params);
            ResultSet set = pstmt.executeQuery();
			T instance = null;
            if (set.next()) {
                instance = MapperUtil.rowMapperObject(set, clazz);
            }
			return instance;
        } catch (SQLException e) {
            e.printStackTrace();
			return null;
        } finally {
            close();
        }
    }

    
    /**
     * @describe: 查询操作，封装到对象集合
     * @param <T>
     * @param sql
     * @param clazz
     * @param params
     * @return
     */
    public static <T> List<T> executeQueryListObject(String sql, Class<T> clazz, Object... params) {
        try {
            beforeOption(sql, params);
            set = pstmt.executeQuery();
            List<T> list = new ArrayList<T>();
            while (set.next()) {
                list.add(MapperUtil.rowMapperObject(set, clazz));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            close();
        }
    }

    
    /**
     * @describe: 查询操作，封装到Map中
     * @param sql
     * @param params
     * @return
     */
    public static Map<String, Object> executeQueryMap(String sql, Object... params) {
        try {
            beforeOption(sql, params);
            ResultSet set = pstmt.executeQuery();
			Map<String, Object> map = null;
            if (set.next()) {
                map = MapperUtil.rowMapperMap(set);
            }
			return map;
        } catch (SQLException e) {
            e.printStackTrace();
			return null;
        } finally {
            close();
        }
    }

    
    /**
     * @describe: 查询操作，封装到List<Map>中
     * @param sql
     * @param params
     * @return
     */
    public static List<Map<String, Object>> executeQueryListMap(String sql, Object... params) {
        try {
            beforeOption(sql, params);
            ResultSet set = pstmt.executeQuery();
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            while (set.next()) {
                list.add(MapperUtil.rowMapperMap(set));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            close();
        }
    }

    
    /**
     * @describe: 操作之前配置
     * @param sql
     * @param params
     * @throws SQLException
     * @return: void
     */
    private static void beforeOption(String sql, Object... params) throws SQLException {
        pstmt = JdbcUtils.getConnection().prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            pstmt.setObject(i + 1, params[i]);
        }
    }

    
    /**
     * @describe: 关闭结果集、通道
     * @throws SQLException
     */
    public static void close() {
        try {
            if (set != null) {
                set.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
			JdbcUtils.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}