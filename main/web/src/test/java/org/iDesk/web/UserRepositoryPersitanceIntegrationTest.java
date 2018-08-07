package org.iDesk.web;

import org.data.persistance.model.Roles;
import org.data.persistance.model.Users;
import org.data.persistance.repository.RoleRepository;
import org.data.persistance.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.security.PersistanceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistanceConfiguration.class }, loader = AnnotationConfigContextLoader.class)
public class UserRepositoryPersitanceIntegrationTest {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	
	@Test
	public final void whenContextIsBootstraped_thenNoException() {

	}
	
	@Test
	public final void whenRoleIsCreated_thenNoException() {
		Roles role = new Roles("Admin");
		roleRepo.save(role);
	}
	
	@Test
	public final void whenEntityIsCreated_thenNoException() {
		Users user = new Users("Admin", "Admin", "admin@admin.com", "admin", "admin123", true);
		userRepo.save(user);
	}
	
	@Test
	public final void whenUserIsFound_thenNoException() {
		Users user = userRepo.findByUsernameAndActive("admin", true);
		System.out.println("User found, User is:"+user.toString());
	}
}
