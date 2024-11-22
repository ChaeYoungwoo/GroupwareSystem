package com.pcwk.ehr.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NConnectionMaker implements ConnectionMaker {

	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		
		String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe"; // 포트번호 다름
		String DB_USER = "scott";
		String DB_PASSWORD = "pcwk";

		Class.forName("oracle.jdbc.driver.OracleDriver"); // 클래즈를 리턴
		Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); // 커넥션 주소값 리턴
		System.out.println("1. conn: " + conn);

		return conn;
	}

}
	