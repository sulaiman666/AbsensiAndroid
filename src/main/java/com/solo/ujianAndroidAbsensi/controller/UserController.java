package com.solo.ujianAndroidAbsensi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solo.ujianAndroidAbsensi.entity.Status;
import com.solo.ujianAndroidAbsensi.entity.User;
import com.solo.ujianAndroidAbsensi.services.UserServices;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserServices userService;
//	BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
	
	@PostMapping("/add")
	public String addUser(@RequestBody User user) {
		String username = user.getUsername();
		if (this.userService.getUserByUserName(username) == null) {
//			String encodedPassword = passEncoder.encode(user.getPassword());
//			user.setPassword(encodedPassword);
			this.userService.addUser(user);
			return "SUCCESS";
		}
		return "USER_ALREADY_EXISTS";
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestBody User user) {
		String username = user.getUsername();
		User userFetch = this.userService.getUserByUserName(username);
		if (user.getPassword().equals(userFetch.getPassword()) && userFetch != null) return "SUCCESS";
//		if (passEncoder.matches(user.getPassword(), userFetch.getPassword())) {
//			return Status.SUCCESS;
//		}
		else return "FAILURE";
	}
	
	@GetMapping("/")
	public List<User> findAllUser() {
		return this.userService.findAllUser();
	}
	
	
}
