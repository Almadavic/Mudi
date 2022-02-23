package br.com.alura.mvc.mudi.repositories;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.mvc.mudi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
	
	@Cacheable("users") // Faz o cache quando solicitar esse tipo de operação
	User findByUsername(String username);
	


}
