package com.lm.login.app.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class User {

	private String fullName;
	private String email;
	private String pwd;
	private Long MobNo;
	private LocalDate dob;
	
}
