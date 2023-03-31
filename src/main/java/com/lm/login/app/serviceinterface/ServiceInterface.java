package com.lm.login.app.serviceinterface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lm.login.app.binding.Login;
import com.lm.login.app.binding.User;
import com.lm.login.app.entity.Product;
import com.lm.login.app.entity.UserMaster;

public interface ServiceInterface {
	
	public boolean saveUser(User user);

	public String login(Login login);

	public Iterable<UserMaster> userInfo();

	public UserMaster userInfo(Integer userId);

	public UserMaster changepwd(Integer userId, UserMaster um);

	public Product registerProduct(Product pro);

	public Iterable<Product> productInfo();

	public Page<Product> getProducts(Pageable pageable);


}
