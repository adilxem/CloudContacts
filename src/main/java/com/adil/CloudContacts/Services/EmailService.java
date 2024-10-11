package com.adil.CloudContacts.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


	// @Autowired
	// private JavaMailSender mailSender;

	// @Value("${spring.mail.properties.fromDomainName}")
	// private String fromDomainName;


	// public void sendEmail(String to, String subject, String body) {

	// 	SimpleMailMessage message = new SimpleMailMessage();

	// 	message.setTo(to);
	// 	message.setSubject(subject);
	// 	message.setText(body);
	// 	message.setFrom(fromDomainName);

	// 	mailSender.send(message);
	// }

}
