package com.example.superhim;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.example.superhim.dao.AdminLoginDao;

@Component
public class TableCreationRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Hello");
		
		ApplicationContext aContext= GetApplicationContext.getApplicationContext();
		AdminLoginDao AD=aContext.getBean(AdminLoginDao.class);
		
		System.out.println();
		long count= AD.count();
		
		if (count == 0) {
			AD.AdminData("admin", "admin");
		}
	}

	
}
