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
 * @describe: 数据库操作工具
 * @author 唐兴甫
 */
public class QueryRunnerDBUtil {
	
	private static QueryRunner queryRunner = new QueryRunner();

	/**
	 * @describe: 增删改
	 * @param sql: 数据库执行的SQL语句
	 * @param params: 执行的SQL语句需要传入的参数(按sql里的序列)
	 */
	public static int executeUpdate(String sql, Object... params) {
		try {
			return queryRunner.update(JdbcUtils.getConnection(), sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	
	/**
	 * @describe: 查询对象集合
	 * @param <T>
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 */
	public static <T> List<T> executeQueryList(String sql, Class<T> clazz, Object... params) {
		try {
			BeanListHandler<T> beanListHandler = new BeanListHandler<T>(clazz);
			return queryRunner.query(JdbcUtils.getConnection(), sql, beanListHandler, params);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * @describe: 查询对象
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return T
	 */
	public static <T> T executeQueryObject(String sql, Class<T> clazz, Object... params) {
		try {
			BeanHandler<T> beanHandler = new BeanHandler<T>(clazz);
			return queryRunner.query(JdbcUtils.getConnection(), sql, beanHandler, params);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * @describe: 查询一个结果
	 * @param <T>
	 * @param sql
	 * @param params
	 * @return
	 */
	public static <T> T executeQueryScalar(String sql, Object ... params) {
		try {
			ScalarHandler<T> scalarHandler = new ScalarHandler<T>();
			return queryRunner.query(JdbcUtils.getConnection(), sql, scalarHandler, params);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * @describe: 查询结果封装到Map
	 * @param sql
	 * @param params
	 * @return
	 */
	public static Map<String, Object> executeQueryMap(String sql, Object ... params) {
		try {
			MapHandler mapHandler = new MapHandler();
			return queryRunner.query(JdbcUtils.getConnection(), sql, mapHandler, params);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * @describe: 查询结果封装到List<Map>
	 * @param sql
	 * @param params
	 * @return
	 */
	public static List<Map<String,Object>> executeQueryListMap(String sql, Object ... params) {
		try {
			MapListHandler mapListHandler = new MapListHandler();
			return queryRunner.query(JdbcUtils.getConnection(), sql, mapListHandler, params);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}