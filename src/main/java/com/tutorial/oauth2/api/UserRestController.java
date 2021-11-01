package com.tutorial.oauth2.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class UserRestController {

	private static final Logger log = LoggerFactory.getLogger(UserRestController.class);
	
	private List<String> userRepository;
	
	public UserRestController() {
		userRepository = new ArrayList<>();
		userRepository.add("hone1");
		userRepository.add("hone2");
	}
	
	@PreAuthorize("#oauth2.hasScope('read_user')")
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public List<String> getAllUsers(){
		return this.userRepository;
	}
	
	@PreAuthorize("#oauth2.hasScope('read_user')")
	@RequestMapping(value="/users/{userId}", method=RequestMethod.GET)
	public List<String> getUserByUserId(@PathVariable String userId) {
		return userRepository.stream().filter(users -> users.equals(userId)).collect(Collectors.toList());
	}
	
	@PreAuthorize("#oauth2.hasScope('create_user')")
	@PostMapping("/users/{userId}")
	public List<String> addUser(@PathVariable String userId){
		userRepository.add(userId);
		return userRepository;  
	}
}
