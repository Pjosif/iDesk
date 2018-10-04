package org.data.persistance.repository;

import org.data.persistance.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> , UserRepositoryCustom{

	Users findByUsernameAndActive(String name, boolean active);
	
	Users findByEmail(String email);
	
	Users findByUsername(String name);
	
}
