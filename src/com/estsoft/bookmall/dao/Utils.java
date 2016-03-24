package com.estsoft.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utils {
	
	public static Connection getConnection(){
	
	Connection conn = null;
	try {
		//1. 드라이버 로드
		Class.forName( "com.mysql.jdbc.Driver" );

		//2. Connection 얻기
		String url = "jdbc:mysql://localhost:3306/bookmall";
		conn = DriverManager.getConnection( url, "bookmall", "bookmall" );
		
	} catch (ClassNotFoundException ex) {
		System.out.println( "드라이버를 찾을 수 없습니다:" + ex );
	} catch (SQLException e) {
		e.printStackTrace();
	} 
	return conn;
	}
}

