package org.data.persistance.repository;

import org.data.persistance.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

	Users findByUsernameAndActive(String name, boolean active);
	
	Users findByEmail(String email);
}
