package org.data.persistance.repository;

import java.util.List;

import org.data.persistance.model.Roles;

public interface RoleRepositoryCustom {

	List<Roles> findListForIds(List<Long> idList);
}
