package org.security.controller;

import java.util.List;

import org.data.persistance.model.Users;
import org.data.persistance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("protected")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/list")
	public ModelAndView viewUser() {
		List<Users> userList = userRepository.findAll();
		return new ModelAndView("userAdm","list",userList);
	}
	
	@RequestMapping("/add")
	public ModelAndView addUser() {
		return new ModelAndView("userAdd");
	}
}
