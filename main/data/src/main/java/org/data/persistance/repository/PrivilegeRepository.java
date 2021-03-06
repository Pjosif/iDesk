package org.data.persistance.repository;

import org.data.persistance.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long>, PrivilegeRepositoryCustom{

	Privilege findByName(String name);
}
