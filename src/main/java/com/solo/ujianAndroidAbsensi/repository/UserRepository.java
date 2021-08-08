package com.solo.ujianAndroidAbsensi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.solo.ujianAndroidAbsensi.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	@Query(value = "Select * FROM user WHERE username = ?1", nativeQuery = true)
	public User getByUsername(String username);
}
