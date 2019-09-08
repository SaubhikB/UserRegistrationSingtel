package com.saubhik.registration.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.saubhik.registration.service.AWSService;
import com.saubhik.registration.service.RegistrationService;
import com.saubhik.registration.util.RegistrationException;
import com.saubhik.registration.util.User;


/*
 * Validation check for user details. Number Format etc.
 * Swagger Documentation
 * Business Exceptions
 * Logger
 * Transaction Management
 * Global Exception Management
 */


@RestController
public class RegistrationController {

	Logger log = LoggerFactory.getLogger(RegistrationController.class);
	
	@Autowired
	private RegistrationService regService;
	
	@Autowired
	AWSService uploadService;
	
	/*
	 * @Autowired EMailService emailSvc;
	 */
	
	@PostMapping(value = "/addUser", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String regUser(@RequestBody @Valid User user, BindingResult result) throws Exception {
		
		if(result.hasErrors()) {
			log.info(user.toString());
			throw new RegistrationException(3l, "Incorrect User details - " + result.getAllErrors());
		}
		
		regService.persistUser(user);
		log.info("User registered successfully");
		return "User registered successfully";
	}
	
	
	@GetMapping(value = "/user/{id}")
	public User fetchUserDet(@PathVariable(name = "id") Long id) throws Exception {
		
		if(id != null) {
			User user = regService.fetchUser(id);
			log.info("User detail found...");
			return user;
		} else {
			throw new RegistrationException(4l, "User Id not passed...");
		}
	}
	
	
	@PostMapping(value = "/updateUser/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateUser(@RequestBody @Valid User user, @PathVariable(name = "id") Long id, BindingResult result) throws Exception {
		
		if(result.hasErrors()) {
			log.info(user.toString());
			throw new RegistrationException(3l, "Incorrect User details - " + result.getAllErrors());
		}
		
		regService.updateUser(user, id);
		log.info("User updated successfully");
		return "User updated successfully";
	}
	
	@GetMapping(value = "/fetchAllUsers")
	public List<User> getListUsers() throws Exception {
	
		List<User> users = regService.fetchUserList();
		if(users != null) {
			
			log.info("Users: " + users);
			return users;
		}
		log.info("No User...");
		return new ArrayList<User>();
	}
	
	
	@GetMapping(value = "/sendNotif/{id}")
	public String sendNotification(@PathVariable(name = "id") String userId) {
		
		//emailService.sendMail();
		return "PLs check Mail Box. ";
			
	}
	
	@PostMapping(value = "/uploadImage")
	public String uploadImage(@RequestParam("imageFile") MultipartFile image) throws IOException {
		
		uploadService.uploadImage(image.getInputStream());
		return "Image Uploaded";
	}
	
	
}
