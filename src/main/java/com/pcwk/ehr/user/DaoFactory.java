package com.pcwk.ehr.user;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

/**
 * XXXDao 생성 : 생성할 모든 XXXDao는 ConnectionMaker를 인자로 가지고 있음.
 * 그래서 밑에 new NConnectionMaker()를 리턴해주는 메소드를 따로 팠음.
 * @author acorn
 */

@Configuration //애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정 정보라는 표시
public class DaoFactory {
	
	/**
	 * UserDao 생성
	 * @return
	 */
	
	@Bean //오브젝트 생성을 담당하는 IoC용 메소드라는 표시
	public UserDao userDao() {
		//ConnectionMaker connectionMaker = new NConnectionMaker();		
		//UserDao userDao = new UserDao(new NConnectionMaker());
		return new UserDao(connectionMaker());
	}
	
//	public BoardDao boardDao() {
//		ConnectionMaker connectionMaker = new NConnectionMaker();		
//		BoardDao userDao = new BoardDao(connectionMaker);
	
//		return newBoardDao(connectionMaker());
//	}
	
	@Bean
	public ConnectionMaker connectionMaker() {
	
		return new NConnectionMaker();
	}
}
