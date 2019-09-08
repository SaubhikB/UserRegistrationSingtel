package com.saubhik.registration.service;

import java.util.List;

import com.saubhik.registration.util.User;


public interface RegistrationService {

	void persistUser(User user) throws Exception;
	User fetchUser(Long id) throws Exception;
	List<User> fetchUserList() throws Exception;
	void updateUser(User user, Long id) throws Exception;
	
}
