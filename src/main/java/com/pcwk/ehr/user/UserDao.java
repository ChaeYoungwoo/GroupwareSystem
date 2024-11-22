package com.pcwk.ehr.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

	ConnectionMaker connectionMaker;
	
	public UserDao() {
		
	}

	public UserDao(ConnectionMaker connectionMaker) {
		this.connectionMaker = new NConnectionMaker();
	}
	
	public void setConnectionMaker(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}

	/*
	 * sb.append("INSERT INTO member(     \n");
	 * sb.append("    user_id,            \n");
	 * sb.append("    name,               \n");
	 * sb.append("    password,           \n");
	 * sb.append("    reg_dt              \n");
	 * sb.append(") VALUES (              \n");
	 * sb.append("?v0,                    \n");
	 * sb.append("?v1,                    \n");
	 * sb.append("?v2,                    \n");
	 * sb.append("SYSDATE)                \n");
	 */
	// 등록
	public int doSave(UserVO inVO) throws ClassNotFoundException, SQLException {
		// 1. DB연결을 위한 Connection
		// 2. SQL을 담은 PreparedStatement,Statement를 생성
		// 3. PreparedStatement를 실행한다.
		// 4. 실행결과 받기 ResultSet 받아서 저장. (X)
		// 5. Connection,PreparedStatement,ResultSet의 자원 반납.
		// 6. JDBC API에 대한 예외 처리
		int flag = 0;

		// 1: DB연결
		Connection conn = connectionMaker.makeConnection();

		StringBuilder sb = new StringBuilder(200);

		sb.append("INSERT INTO member(     \n");
		sb.append("    user_id,            \n");
		sb.append("    name,               \n");
		sb.append("    password,           \n");
		sb.append("    reg_dt              \n");
		sb.append(") VALUES (              \n");
		sb.append("	   ?,                  \n");
		sb.append("	   ?,                  \n");
		sb.append("	   ?,                  \n");
		sb.append("SYSDATE)                \n");
		System.out.println("2.sql: \n" + sb.toString());
		
		//위에 sql 쿼리를 sb에 담고, 이걸 preparedStatement로 저장
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		// 2. 인자값으로 줄 객체에 값 설정
		pstmt.setString(1, inVO.getUserId()); // 위에 sb.append 구문의 1번째 인자에 inVO.getUserId()를 넣겠다.
		pstmt.setString(2, inVO.getName()); // 위에 sb.append 구문의 2번째 인자에 inVO.getName()를 넣겠다.
		pstmt.setString(3, inVO.getPassword()); // 위에 sb.append 구문의 3번째 인자에 inVO.getPassword()를 넣겠다.

		System.out.println("3.param:" + inVO.toString());

		// DML: executeUpdate()
		flag = pstmt.executeUpdate(); //executeUpdate()는 INSERT, UPDATE, DELETE와 같은 DML을 실행할 때 쓰는 것
							          //결과는 행의 개수를 반환해서 int타입
		System.out.println("4.flag:" + flag);

		// 5 자원 반납
		pstmt.close();
		conn.close();

		return flag;
	}

	// 단건조회
	/*
	 * 
	 * sb.append("SELECT                                           \n");
	 * sb.append("	user_id,                                       \n");
	 * sb.append("	name,                                          \n");
	 * sb.append("	password,                                      \n");
	 * sb.append("	TO_CHAR(reg_dt,'YYYY/MM/DD HH24:MI:SS') reg_dt \n");
	 * sb.append("FROM                                             \n");
	 * sb.append("	member                                         \n");
	 * sb.append("WHERE user_id = :user_id                         \n");
	 */
	public UserVO doSelectOne(UserVO inVO) throws ClassNotFoundException, SQLException {
		// 1. DB연결을 위한 Connection
		// 2. SQL을 담은 PreparedStatement,Statement를 생성
		// 3. PreparedStatement를 실행한다.
		// 4. 실행결과 받기 ResultSet 받아서 저장.
		// 5. Connection,PreparedStatement,ResultSet의 자원 반납.
		// 6. JDBC API에 대한 예외 처리
		UserVO outVO = null;

		// 1: DB 연결
		Connection conn = connectionMaker.makeConnection();

		// 2.커넥션 통해 preparedstatement 만들기
		StringBuilder sb = new StringBuilder(200);
		
		sb.append("SELECT                                          \n");
		sb.append("	user_id,                                       \n");
		sb.append("	name,                                          \n");
		sb.append("	password,                                      \n");
		sb.append("	TO_CHAR(reg_dt,'YYYY/MM/DD HH24:MI:SS') reg_dt \n");
		sb.append("FROM                                            \n");
		sb.append("		member                                     \n");
		sb.append("WHERE user_id = ?                               \n"); //여기에 넣어줌 (AA)
		System.out.println("2.sql: \n" + sb.toString());
		
		//SQL 문장을 담은 PreparedStatement 생성 및 실행 
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		pstmt.setString(1, inVO.getUserId());  //inVO의 userID를 받아서 user_ID에 넣어주기 (AA) //이 값을 토대로 정보를 조회하는 sql문.

		//인자로 쓰일 객체에 값 제대로 들어왔나 확인하는 용도
		System.out.println("3.param: +" + inVO.toString());

		// 쿼리문 실행 
		ResultSet rs = pstmt.executeQuery(); //execueteQuery()는 SELECE문과 같은 쿼리를 실행할 때 사용  // 찾은 정보 담는 Resultet
										     //결과의 return type은 ResultSet

		if (rs.next()) {				     // user_ID가 맞는 데이터가 rs에 담겨있다면,
			outVO = new UserVO(); 		     //outVO 객체 생성

			outVO.setUserId(rs.getString("user_id"));  //sql 실행문을 토대로 찾은 데이터를 순서대로 outVO 객체에 넣기
			outVO.setName(rs.getString("name"));
			outVO.setPassword(rs.getString("password"));
			outVO.setRegdt(rs.getString("reg_dt"));

			System.out.println("4.OutVO: + " + outVO.toString()); //outVO 조회
		}

		// 5: 자원반납은 항상 밑에서 위로
		rs.close();
		pstmt.close();
		conn.close();

		return outVO;
	}

}
