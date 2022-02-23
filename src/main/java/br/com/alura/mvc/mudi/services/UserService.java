package br.com.alura.mvc.mudi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public void insert(User user) {
		repository.save(user);
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findByUserName(String username) {
		return repository.findByUsername(username);
	}

	

	public List<User> findAllByUsuario() {
		return repository.findAll();
	}
	
	

}
