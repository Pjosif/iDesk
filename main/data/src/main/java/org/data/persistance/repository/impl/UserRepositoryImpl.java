package org.data.persistance.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.data.persistance.model.Users;
import org.data.persistance.repository.UserRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Users> searchUsers(String firstName, String lastName, String username, String role) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Users> cq = cb.createQuery(Users.class);
		Root<Users> user = cq.from(Users.class);
		
		List<Predicate> predicateList = new ArrayList<Predicate>();
		if(!firstName.isEmpty()) {
			Predicate fn = cb.like(user.get("firstName"), firstName);
			predicateList.add(fn);
		}
		if(!lastName.isEmpty()) {
			Predicate ln = cb.like(user.get("lastName"), lastName);
			predicateList.add(ln);
		}
		if(!username.isEmpty()) {
			Predicate un = cb.like(user.get("username"), username);
			predicateList.add(un);
		}
		if(!role.isEmpty()) {
			Predicate rl = cb.equal(user.get("role").get("id"), Long.parseLong(role));
			predicateList.add(rl);
		}
		cq.select(user);
		Predicate combinedPredicate = cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
		cq.where(combinedPredicate);
		
		Query query = entityManager.createQuery(cq);
		
		return query.getResultList();
	}

}
