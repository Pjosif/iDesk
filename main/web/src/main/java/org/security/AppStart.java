package org.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AppStart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
		System.out.println(encoder.encode("admin123"));
	}

}
