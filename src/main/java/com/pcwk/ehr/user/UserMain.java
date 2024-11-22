package com.pcwk.ehr.user;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserMain {
	UserDao dao;
	UserVO user;
	 
	ApplicationContext context;
	
	public UserMain() {	
		
		//context = new AnnotationConfigApplicationContext(DaoFactory.class);
		context = new GenericXmlApplicationContext("applicationContext.xml");
		
		dao = context.getBean("userDao", UserDao.class);
		//dao = (UserDao)context.getBean("userDao"); //위에 방법이랑 똑같음 인자 한 개를 쓸 건지 두 개를 쓸 건지
		user = new UserVO("James011", "이상무01", "4321","사용하지 않음");
	}

	public void doSave() {
		System.out.println("doSave");
		try {
			int flag = dao.doSave(user);
			if(1 == flag) {
				System.out.println("등록 성공");
			} else {
				System.out.println("등록 실패");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void doSelectOne() {
		System.out.println("doSelectOne");
		try {
			UserVO outVO = dao.doSelectOne(user);
			
			if(null != outVO) {
				System.out.println("조회 성공: " + outVO.toString());
			} else {
				System.out.println("조회 실패");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		UserMain main = new UserMain();
		//등록
		main.doSave();
		
		//단건조회
		main.doSelectOne();

	}
}
