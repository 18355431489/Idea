package com.util.jdbc;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;


/**
 * 数据库操作工具类
 * @author 唐小甫
 * @datetime 2020-12-05 23:12:27
 */
public class QueryRunnerDBUtil {
	
	private static QueryRunner queryRunner = new QueryRunner();


	/**
	 * 增删改
	 * @param sql
	 * @param params
	 * @return int
	 * @author 唐小甫
	 * @datetime 2020-12-05 23:12:43
	 */
	public static int executeUpdate(String sql, Object... params) {
		try {
			return queryRunner.update(JdbcUtil.getConnection(), sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
    
    
    /**
     * 查询单个结果
     * @param <T>
     * @param sql
     * @param params
     * @return T
     * @author 唐小甫
     * @datetime 2020-12-05 23:16:50
     */
    public static <T> T executeQueryScalar(String sql, Object ... params) {
        try {
            ScalarHandler<T> scalarHandler = new ScalarHandler<T>();
            return queryRunner.query(JdbcUtil.getConnection(), sql, scalarHandler, params);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    /**
     * 查询对象
     * @param <T>
     * @param sql
     * @param clazz
     * @param params
     * @return T
     * @author 唐小甫
     * @datetime 2020-12-05 23:14:53
     */
    public static <T> T executeQueryObject(String sql, Class<T> clazz, Object... params) {
        try {
            BeanHandler<T> beanHandler = new BeanHandler<T>(clazz);
            return queryRunner.query(JdbcUtil.getConnection(), sql, beanHandler, params);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

	
	/**
	 * 查询对象集合
	 * @param <T>
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return List<T>
	 * @author 唐小甫
	 * @datetime 2020-12-05 23:14:14
	 */
	public static <T> List<T> executeQueryList(String sql, Class<T> clazz, Object... params) {
		try {
			BeanListHandler<T> beanListHandler = new BeanListHandler<T>(clazz);
			return queryRunner.query(JdbcUtil.getConnection(), sql, beanListHandler, params);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
    
    
    /**
     * 查询结果封装到Map中
     * @param sql
     * @param params
     * @return Map<String,Object>
     * @author 唐小甫
     * @datetime 2020-12-05 23:15:32
     */
    public static Map<String, Object> executeQueryMap(String sql, Object ... params) {
        try {
            MapHandler mapHandler = new MapHandler();
            return queryRunner.query(JdbcUtil.getConnection(), sql, mapHandler, params);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    /**
     * 查询结果封装到List<Map>中
     * @param sql
     * @param params
     * @return List<Map<String,Object>>
     * @author 唐小甫
     * @datetime 2020-12-05 23:15:56
     */
    public static List<Map<String,Object>> executeQueryListMap(String sql, Object ... params) {
        try {
            MapListHandler mapListHandler = new MapListHandler();
            return queryRunner.query(JdbcUtil.getConnection(), sql, mapListHandler, params);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}