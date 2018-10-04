package org.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.data.persistance.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@Autowired
	RoleRepository roleRepo;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "redirect:/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String admin() {
		return "home";
	}

	@RequestMapping(value = "/protected/home", method = RequestMethod.GET)
	public String protHome() {
		return ("redirect:/home");
	}

//	@RequestMapping(value = "/update", method = RequestMethod.GET)
//	@ResponseBody
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	public String update() {
//		return "record update";
//	}

	 @RequestMapping(value = "/logout", method = RequestMethod.GET)
	 public String logoutPage(HttpServletRequest request, HttpServletResponse resoponse) {
	 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	 if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, resoponse, auth);
	 }
	 return "redirect:/";
	 }
}
