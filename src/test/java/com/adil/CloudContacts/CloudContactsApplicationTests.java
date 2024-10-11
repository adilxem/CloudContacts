package com.adil.CloudContacts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.adil.CloudContacts.Services.EmailService;

@SpringBootTest
class CloudContactsApplicationTests {
	
	@Test
	void contextLoads() {
	}

	@Autowired
	private EmailService emailService;

	@Test
	void sendEmailTest() {

		emailService.sendEmail("23352004@pondiuni.ac.in", "Testing Email Service", "CloudContacts email verification link");
	}

}
