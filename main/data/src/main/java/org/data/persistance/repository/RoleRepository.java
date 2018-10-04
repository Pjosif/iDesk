package org.data.persistance.repository;

import org.data.persistance.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Long>, RoleRepositoryCustom{

	Roles findByName(String name);
}
