package com.xqq.mvc.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


/**
 * JDBC 操作的工具类
 * @author Administrator
 *
 */
public class JdbcUtils {

	/**
	 * 释放Connection 链接
	 * @param connection
	 */
	public static void releaseConnection(Connection connection){
		
	}
	
	private static DataSource dataSource = null;
	
	static{
		//数据源只能被创建一次  必须放在静态代码块里
		dataSource = new ComboPooledDataSource("mvcapp");
	}
	/**
	 * 返回数据源的一个Connection
	 * @return
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
}
