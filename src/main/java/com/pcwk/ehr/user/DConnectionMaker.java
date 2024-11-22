package com.pcwk.ehr.user;

import java.sql.Connection;
import java.sql.SQLException;

public enum DConnectionMaker implements ConnectionMaker {
	;

	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		
		return null;
	}

}
