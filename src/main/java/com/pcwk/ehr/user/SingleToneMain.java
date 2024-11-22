package com.pcwk.ehr.user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingleToneMain {

	public static void main(String[] args) {
		
		DaoFactory factory = new DaoFactory();
		UserDao dao01 = factory.userDao();
		UserDao dao02 = factory.userDao();
		
		System.out.println("dao01:" + dao01); //dao01:com.pcwk.ehr.user.UserDao@15db9742
		System.out.println("dao02:" + dao02); //dao02:com.pcwk.ehr.user.UserDao@6d06d69c
		System.out.println("---------------------------------------------------------");
		
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		
		UserDao dao10 = (UserDao) context.getBean("userDao");
		UserDao dao12 = context.getBean("userDao", UserDao.class);
		System.out.println("dao10:" + dao10);
		System.out.println("dao12:" + dao12);
//		dao10:com.pcwk.ehr.user.UserDao@62e136d3
//		dao12:com.pcwk.ehr.user.UserDao@62e136d3
	}

}
