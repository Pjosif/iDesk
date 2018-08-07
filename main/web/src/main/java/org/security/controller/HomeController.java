package org.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
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
//
//	@RequestMapping(value = "/user", method = RequestMethod.GET)
//	public String user() {
//		return "protected/userAdm";
//	}

	// @RequestMapping(value = "/user", method = RequestMethod.GET)
	// public String user() {
	// return "admin";
	// }

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String update() {
		return "record update";
	}

	// @RequestMapping(value = "/logout", method = RequestMethod.GET)
	// public String logoutPage(HttpServletRequest request, HttpServletResponse
	// resoponse) {
	// Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	// if (auth != null) {
	// new SecurityContextLogoutHandler().logout(request, resoponse, auth);
	// }
	// return "redirect:/";
	// }
}
