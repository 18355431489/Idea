package com.util.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 数据库操作工具类
 * @author 唐小甫
 * @datetime 2020-12-05 23:22:27
 */
public class DBUtil {

    
    /**
     * 增删改
     * @param sql
     * @param params
     * @return int
     * @author 唐小甫
     * @datetime 2020-12-05 23:23:57
     */
    public static int executeUpdate(String sql, Object... params) {
        PreparedStatement prepareStatement = null;
        try {
            prepareStatement = beforeOption(sql, params);
            return prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            close(prepareStatement, null);
        }
    }


    /**
     * 查询单个结果
     * @param <T>
     * @param sql
     * @param params
     * @return T
     * @author 唐小甫
     * @datetime 2020-12-05 23:25:31
     */
    public static <T> T executeQuerySimple(String sql, Object... params) {
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        try {
            prepareStatement = beforeOption(sql, params);
            resultSet = prepareStatement.executeQuery();
			T instance = null;
            if (resultSet.next()) {
                instance = MapperUtil.rowMapperSimple(resultSet);
            }
			return instance;
        } catch (SQLException e) {
            e.printStackTrace();
			return null;
        } finally {
            close(prepareStatement, resultSet);
        }
    }

    
    /**
     * 查询单列数据
     * @param <T>
     * @param sql
     * @param params
     * @return List<T>
     * @author 唐小甫
     * @datetime 2020-12-05 23:28:48
     */
    public static <T> List<T> executeQueryListSimple(String sql, Object... params) {
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        try {
            prepareStatement = beforeOption(sql, params);
            resultSet = prepareStatement.executeQuery();
            List<T> list = new ArrayList<T>();
            while (resultSet.next()) {
                list.add(MapperUtil.rowMapperSimple(resultSet));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            close(prepareStatement, resultSet);
        }
    }

    
    /**
     * 查询单条记录，封装到对象中
     * @param <T>
     * @param sql
     * @param clazz
     * @param params
     * @return T
     * @author 唐小甫
     * @datetime 2020-12-05 23:29:39
     */
    public static <T> T executeQueryObject(String sql, Class<T> clazz, Object... params) {
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        try {
            prepareStatement = beforeOption(sql, params);
            resultSet = prepareStatement.executeQuery();
			T instance = null;
            if (resultSet.next()) {
                instance = MapperUtil.rowMapperObject(resultSet, clazz);
            }
			return instance;
        } catch (SQLException e) {
            e.printStackTrace();
			return null;
        } finally {
            close(prepareStatement, resultSet);
        }
    }

    
    /**
     * 查询结果封装到对象集合中
     * @param <T>
     * @param sql
     * @param clazz
     * @param params
     * @return List<T>
     * @author 唐小甫
     * @datetime 2020-12-05 23:30:17
     */
    public static <T> List<T> executeQueryListObject(String sql, Class<T> clazz, Object... params) {
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        try {
            prepareStatement = beforeOption(sql, params);
            resultSet = prepareStatement.executeQuery();
            List<T> list = new ArrayList<T>();
            while (resultSet.next()) {
                list.add(MapperUtil.rowMapperObject(resultSet, clazz));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            close(prepareStatement, resultSet);
        }
    }

    
    /**
     * 查询单条记录，封装到Map中
     * @param sql
     * @param params
     * @return Map<String,Object>
     * @author 唐小甫
     * @datetime 2020-12-05 23:30:59
     */
    public static Map<String, Object> executeQueryMap(String sql, Object... params) {
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        try {
            prepareStatement = beforeOption(sql, params);
            resultSet = prepareStatement.executeQuery();
			Map<String, Object> map = null;
            if (resultSet.next()) {
                map = MapperUtil.rowMapperMap(resultSet);
            }
			return map;
        } catch (SQLException e) {
            e.printStackTrace();
			return null;
        } finally {
            close(prepareStatement, resultSet);
        }
    }

    
    /**
     * 查询结果集封装到Map集合中
     * @param sql
     * @param params
     * @return List<Map<String,Object>>
     * @author 唐小甫
     * @datetime 2020-12-05 23:31:16
     */
    public static List<Map<String, Object>> executeQueryListMap(String sql, Object... params) {
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        try {
            prepareStatement = beforeOption(sql, params);
            resultSet = prepareStatement.executeQuery();
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            while (resultSet.next()) {
                list.add(MapperUtil.rowMapperMap(resultSet));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            close(prepareStatement, resultSet);
        }
    }

    
    /**
     * 操作之前的配置
     * @param sql
     * @param params
     * @throws SQLException void
     * @author 唐小甫
     * @datetime 2020-12-05 23:32:00
     */
    private static PreparedStatement beforeOption(String sql, Object... params) throws SQLException {
        PreparedStatement prepareStatement = JdbcUtil.getConnection().prepareStatement(sql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                prepareStatement.setObject(i + 1, params[i]);
            }
        }
        return prepareStatement;
    }


    /**
     * 关闭结果集、通道
     * @param prepareStatement
     * @param resultSet void
     * @author 唐小甫
     * @datetime 2020-12-06 00:57:51
     */
    public static void close(PreparedStatement prepareStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (prepareStatement != null) {
                prepareStatement.close();
            }
			JdbcUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}