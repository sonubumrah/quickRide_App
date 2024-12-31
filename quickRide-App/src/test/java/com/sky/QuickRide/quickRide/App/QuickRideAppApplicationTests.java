package com.sky.QuickRide.quickRide.App;

import com.sky.QuickRide.quickRide.App.services.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuickRideAppApplicationTests {

	@Autowired
	private EmailSenderService emailSenderService;

	@Test
	void contextLoads() {
		emailSenderService.sendMail("xecip88888@evasud.com","This is Testing mail" ,
				"mail for an application");
	}
	@Test
	void sendEmailMultiple(){
		String emails[]={
				"xecip88888@evasud.com",
				"sonukumaryadav88875@gmail.com",
				"sonuyadavkarma@gmail.com"
		};
		emailSenderService.sendEmail(emails,"from quickRide app","Hi all everyone ");

	}

}
