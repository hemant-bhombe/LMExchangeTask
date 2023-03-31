package com.lm.login.app.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lm.login.app.binding.Login;
import com.lm.login.app.binding.User;
import com.lm.login.app.entity.Product;
import com.lm.login.app.entity.UserMaster;
import com.lm.login.app.repository.ProductRepository;
import com.lm.login.app.repository.UserRepository;
import com.lm.login.app.serviceinterface.ServiceInterface;

@Service
public class LoginService implements ServiceInterface {

	@Autowired
	private UserRepository ur;
	
	@Autowired
	private ProductRepository pr;
	
	
	@Override
	public boolean saveUser(User user) {
		
		UserMaster entity =new UserMaster();
		BeanUtils.copyProperties(user, entity);
		UserMaster save = ur.save(entity);
		
		return save.getUserId()!=null;
	}


	@Override
	public String login(Login login) {
				
	 UserMaster entity = ur.findByEmailAndPwd(login.getEmail(), login.getPwd());
		
		if(entity==null) {
			return "Invalid Credential";
		}else {
			return "Success";
		}
	}


	@Override
	public UserMaster changepwd(Integer userId, UserMaster um) {
		
		Optional<UserMaster> op = ur.findById(userId);
		if(op.isPresent())
		{
			UserMaster umid = op.get();
			
			umid.setPwd(um.getPwd());
			
			return ur.save(umid);
		}
		return null;
	}


	@Override
	public Iterable<UserMaster> userInfo() {
		
		return ur.findAll();
	}


	@Override
	public UserMaster userInfo(Integer userId) {
		
		Optional<UserMaster> op = ur.findById(userId);
		if(op.isPresent())
		{
			UserMaster um = op.get();
			
			return um;
		}
		return null;
	}


	@Override
	public Product registerProduct(Product pro) {
		
		return pr.save(pro);
	}


	@Override
	public Iterable<Product> productInfo() {
		
		return pr.findAll();
	}
	
	@Override
	public Page<Product> getProducts(Pageable pageable) {
		
		return pr.findAll(pageable);
	}




}
