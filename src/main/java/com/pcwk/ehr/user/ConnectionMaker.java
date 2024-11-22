package com.pcwk.ehr.user;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
	/*
	 * DB Connection 생성 및 return
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection makeConnection() throws ClassNotFoundException, SQLException;
	
	
	
	
	
}
