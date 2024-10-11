package com.adil.CloudContacts.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.adil.CloudContacts.Helper.Message;
import com.adil.CloudContacts.Helper.MessageType;
import com.adil.CloudContacts.Model.User;
import com.adil.CloudContacts.Services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UserService service;


	// @GetMapping("/verify-email")
	// public String verifyEmail(@RequestParam("token") String token, HttpSession session) {

	// 	User user = service.getUserByEmailToken(token);

	// 	if (user != null) {
			
	// 		if (user.isEnabled()) {

	// 			session.setAttribute("message", Message.builder()
	// 				.content("Email Verified! Login to continue...")
	// 				.type(MessageType.green)
	// 				.build()
	// 				);
				
	// 			return "success-page";
				
	// 		}
			
			
	// 		else if(user.getEmailToken().equals(token)) {
				
	// 			user.setEmailVerified(true);
	// 			user.setEnabled(true);

	// 			service.updateUser(user.getId(), user);

	// 			session.setAttribute("message", Message.builder()
	// 				.content("Email Verified! Login to continue...")
	// 				.type(MessageType.green)
	// 				.build()
	// 				);


	// 			return "success-page";
	// 		}



	// 		session.setAttribute("message", Message.builder()
	// 		.content("Something went wrong")
	// 		.type(MessageType.red)
	// 		.build()
	// 		);

	// 		return "error-page";
	// 	}


	// 	session.setAttribute("message", Message.builder()
	// 		.content("Wrong Token! Email not verified")
	// 		.type(MessageType.red)
	// 		.build()
	// 		);
		
	// 	return "error-page";
	// }
}
