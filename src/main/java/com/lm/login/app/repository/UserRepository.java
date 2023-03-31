package com.lm.login.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lm.login.app.entity.Product;
import com.lm.login.app.entity.UserMaster;

@Repository
public interface UserRepository extends JpaRepository<UserMaster, Integer> {
	
	public UserMaster findByEmailAndPwd(String email, String pwd);
	
	public UserMaster findByEmail(String email);

}
