package com.solo.ujianAndroidAbsensi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solo.ujianAndroidAbsensi.entity.User;
import com.solo.ujianAndroidAbsensi.repository.UserRepository;

@Service
public class UserServices implements UserServiceInterface{
	@Autowired
	UserRepository userRepo;
	
	@Override
	public User getUserByUserName(String username) {
		// TODO Auto-generated method stub
		return this.userRepo.getByUsername(username);
	}

	@Override
	public String addUser(User user) {
		// TODO Auto-generated method stub
		this.userRepo.save(user);
		return "User berhasil ditambahkan";
	}

	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return this.userRepo.findAll();
	}

}
