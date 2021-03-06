package com.medievalgrosbill.services.users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medievalgrosbill.database.base.BaseCRUDRepository;
import com.medievalgrosbill.database.users.UserRepository;
import com.medievalgrosbill.models.User;
import com.medievalgrosbill.services.base.BaseService;

@Service
public class UserService extends BaseService<User> {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	protected BaseCRUDRepository<User> getCRUDRepository() {
		// TODO Auto-generated method stub
		return this.userRepository;
	}

	@Override
	protected List<User> setItemsByCriterias(User item, List<User> result) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public User findByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}
	
	public User findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}
	
	public List<User> findByActive(Integer active){
		return this.userRepository.findByActive(active);
	}
	
	public User findByUsernameAndEmail(String username, String email) {
		return this.userRepository.findByUsernameAndEmail(username, email);
	}
	
	public Optional<User> findById(Integer id){
		return this.userRepository.findById(id);
	}
}
