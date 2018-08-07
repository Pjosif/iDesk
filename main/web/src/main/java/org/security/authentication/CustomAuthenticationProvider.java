package org.security.authentication;

import org.data.persistance.model.Users;
import org.data.persistance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {

		Users user = userRepo.findByUsernameAndActive(auth.getName(),true);
		if(user==null) {
			throw new BadCredentialsException("Invalid username or password");
		}
		final Authentication result = super.authenticate(auth);
		return new UsernamePasswordAuthenticationToken(user, result.getCredentials(),result.getAuthorities());
	}
}
