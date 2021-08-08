package com.solo.ujianAndroidAbsensi.services;

import java.util.List;

import com.solo.ujianAndroidAbsensi.entity.User;

public interface UserServiceInterface {
	public User getUserByUserName(String username);
	public String addUser(User user);
	public List<User> findAllUser();
}
