package org.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.data.persistance.model.Privilege;
import org.data.persistance.model.Roles;
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
			return new User(activeUser.getUsername(),activeUser.getPassword(),activeUser.isActive(),true,true,true,getAuthorities(activeUser.getRoles()));
		} catch (Exception e) {

			throw new RuntimeException(e);
		}
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Roles> roles){
		return getGrantedAuthorities(getPrivileges(roles));
	}
	
	private List<String> getPrivileges(Collection<Roles> roles){
		List<String> privileges = new ArrayList<>();
		List<Privilege> collection = new ArrayList<>();
		for (Roles role : roles) {
			collection.addAll(role.getPrivilieges());
		}
		for(Privilege item: collection) {
			privileges.add(item.getName());
		}
		return privileges;
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges){
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}

}
