package com.saubhik.registration.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.saubhik.registration.domain.UserDetails;
import com.saubhik.registration.repository.UserDetailsRepository;
import com.saubhik.registration.service.RegistrationService;
import com.saubhik.registration.util.RegistrationException;
import com.saubhik.registration.util.User;

@Component
public class RegistrationServiceImpl implements RegistrationService {

	Logger log = LoggerFactory.getLogger(RegistrationServiceImpl.class);
	
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	private ModelMapper mapper = new ModelMapper();
	
	@Override
	public void persistUser(User user) throws Exception {
		userDetailsRepository.save(mapper.map(user, UserDetails.class));
		log.info("User saved...");
	}

	@Override
	@Cacheable(value = "user", key = "#id")
	public User fetchUser(Long id) throws Exception {

		Optional<UserDetails> optional = userDetailsRepository.findById(id);
		if(optional != null && optional.get() != null) {
			log.info("User found: " + optional.get().getName());
			return mapper.map(optional.get(), User.class);
		} else {
			throw new RegistrationException(1l, "No such User...");
		}
	}

	@Override
	@Cacheable(value = "userList")
	public List<User> fetchUserList() throws Exception {

		Iterable<UserDetails> iterableUsers = userDetailsRepository.findAll();

		List<User> userList = new ArrayList<User>();
		iterableUsers.forEach(userDetail -> userList.add(mapper.map(userDetail, User.class)));
		
		log.info("Total users found: " + userList.size());
		return userList;
	}

	@Override
	public void updateUser(User user, Long id) throws Exception {

		Optional<UserDetails> optional = userDetailsRepository.findById(id);
		if(optional != null && optional.get() != null) {
			log.info("User to be updated: " + optional.get().getName());
			
			UserDetails details = optional.get();
			details.setAddress(user.getAddress());
			details.setEmail(user.getEmail());
			details.setName(user.getName());
			details.setPhone(user.getPhone());
			
			userDetailsRepository.save(details);
			log.info("User updated...");
		} else {
			throw new RegistrationException(2l, "No such User to update...");
		}
	}

}
