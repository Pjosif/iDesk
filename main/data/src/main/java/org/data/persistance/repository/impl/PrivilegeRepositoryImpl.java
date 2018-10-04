package org.data.persistance.repository.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.data.persistance.model.Privilege;
import org.data.persistance.repository.PrivilegeRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PrivilegeRepositoryImpl implements PrivilegeRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Privilege> findListForIds(List<Long> idList) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Privilege> cq = cb.createQuery(Privilege.class);
		Root<Privilege> privilege = cq.from(Privilege.class);
		Expression<Collection<Long>> ids = privilege.get("id");

		List<Predicate> predicateList = new ArrayList<Predicate>();
		if (!idList.isEmpty()) {
			Predicate idPredicate = ids.in(idList);
			predicateList.add(idPredicate);
		}
		cq.select(privilege);
		cq.where(predicateList.toArray(new Predicate[predicateList.size()]));

		Query query = entityManager.createQuery(cq);

		return query.getResultList();
	}

}
