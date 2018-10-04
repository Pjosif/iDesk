package org.iDesk.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.data.persistance.model.Privilege;
import org.data.persistance.model.Roles;
import org.data.persistance.model.Users;
import org.data.persistance.repository.PrivilegeRepository;
import org.data.persistance.repository.RoleRepository;
import org.data.persistance.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.security.PersistanceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistanceConfiguration.class }, loader = AnnotationConfigContextLoader.class)
public class UserRepositoryPersitanceIntegrationTest {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private PrivilegeRepository privelegeRepo;

	@Test
	public final void whenContextIsBootstraped_thenNoException() {

	}

	@Test
	public final void whenPrivilegeIsCreatedAndAssignedToRole_thenNoException() {
		Privilege privilege = new Privilege("ADMIN_PRIVILEGE");
		Roles role = new Roles("ROLE_ADMIN");	
		privelegeRepo.save(privilege);
		List<Privilege> privileges = new ArrayList<>();
		privileges.add(privilege);
		//assertEquals(0, role.getPrivilieges().size());
		role.setPrivilieges(privileges);
		roleRepo.save(role);
		
		assertNotNull(role);
	}
//
//	@Test
//	public final void whenRoleIsCreated_thenNoException() {
//		Roles role = new Roles("ROLE_ADMIN");
//		
//		roleRepo.save(role);
//	}

	@Test
	public final void whenUserIsCreatedAndRoleIsAssigned_thenNoException() {
		Roles role = roleRepo.findOne((long) 1);
		List<Roles> roleList = new ArrayList<>();
		roleList.add(role);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
		Users user = new Users("Admin", "Admin", "admin@admin.com", "admin", encoder.encode("admin123"), true);
		user.setRoles(roleList);
		userRepo.save(user);
	}
	
	 @Test
	 public final void whenUserIsFound_thenNoException() {
	 Users user = userRepo.findByUsernameAndActive("admin", true);
	 System.out.println("User found, User is:" + user.toString());
	 }
	
//	@SuppressWarnings("unchecked")
//	@Test
//	public final void whenUserSearchCompleats_thenNoException() {
//
//		String firstName = "Admin";
//		String lastName = "";
//		String username = "";
//		String role = "";
//
//		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Users> cq = cb.createQuery(Users.class);
//		Root<Users> user = cq.from(Users.class);
//
//		List<Predicate> predicateList = new ArrayList<Predicate>();
//		if (!firstName.isEmpty()) {
//			Predicate fn = cb.like(user.get("firstName"), firstName);
//			predicateList.add(fn);
//		}
//		if (!lastName.isEmpty()) {
//			Predicate ln = cb.like(user.get("lastName"), lastName);
//			predicateList.add(ln);
//		}
//		if (!username.isEmpty()) {
//			Predicate un = cb.like(user.get("username"), username);
//			predicateList.add(un);
//		}
//		if (!role.isEmpty()) {
//			Predicate rl = cb.equal(user.get("role.role"), role);
//			predicateList.add(rl);
//		}
//		cq.select(user);
//		Predicate combinedPredicate = cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
//
//		cq.where(combinedPredicate);
//
//		Query query = entityManager.createQuery(cq);
//
//		List<Users> result = query.getResultList();
//		for (Users users : result) {
//			System.out.println(users.toString());
//		}
//	}

}
