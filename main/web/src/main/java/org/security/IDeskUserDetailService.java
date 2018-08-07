package org.security;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.data.persistance.model.Users;
import org.data.persistance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailService")
@Transactional
@ComponentScan({"org.data.persistance"})
public class IDeskUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Users activeUser = userRepo.findByUsernameAndActive(username, true);
			if (activeUser == null) {
				throw new UsernameNotFoundException("No user found for give username: " + username);
			}
			GrantedAuthority authority = new SimpleGrantedAuthority(activeUser.getRole().getRole());
			return new User(activeUser.getUsername(),activeUser.getPassword(),activeUser.isActive(),true,true,true,Arrays.asList(authority));
		} catch (Exception e) {

			throw new RuntimeException(e);
		}
	}

}
