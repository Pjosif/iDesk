package org.data.persistance.repository;

import java.util.List;

import org.data.persistance.model.Privilege;

public interface PrivilegeRepositoryCustom {

	List<Privilege> findListForIds(List<Long> idList);
}
