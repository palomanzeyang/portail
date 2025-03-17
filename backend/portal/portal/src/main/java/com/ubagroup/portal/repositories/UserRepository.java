package com.ubagroup.portal.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import com.ubagroup.portal.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	Optional<User> findByEmail(String email);

}
