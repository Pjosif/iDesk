package org.security.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.data.persistance.model.Roles;
import org.data.persistance.model.Users;
import org.data.persistance.repository.RoleRepository;
import org.data.persistance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	MessageSource messageSource;

	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Roles.class, new RoleEditor());
	}

	@ModelAttribute("allRoles")
	public List<Roles> populateRoles() {
		ArrayList<Roles> roleList = (ArrayList<Roles>) roleRepository.findAll();
		return roleList;
	}

	@RequestMapping("/userlist")
	public ModelAndView viewUser() {
		ModelAndView model = new ModelAndView("protected/userAdm");
		List<Users> userList = userRepository.findAll();
		model.addObject("list", userList);
		model.addObject("user", new Users());
		return model;
	}

	@RequestMapping("/addUser")
	public ModelAndView addUser() {
		ModelAndView model = new ModelAndView("protected/userAdd");
		model.addObject("user", new Users());
		return model;
	}

	@RequestMapping("/editUser-{id}")
	public ModelAndView editUser(@PathVariable Long id) {
		ModelAndView model = new ModelAndView("protected/userAdd");
		Users user = userRepository.findOne(id);
		model.addObject("user", user);
		return model;
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public String saveUser(@Valid @ModelAttribute("user") Users user, BindingResult result, ModelAndView model) {
		if (user.getRoles().isEmpty()) {
			result.rejectValue("roles", "NotSelect.user.roles");
		}
		if (result.hasErrors()) {
			return "protected/userAdd";
		}

		if (user.getId() == null) {
			Users emailDuplicate = userRepository.findByEmail(user.getEmail());
			if (emailDuplicate != null) {
				result.rejectValue("email", "Duplicate.user.email");
				return "protected/userAdd";
			}
			Users usernameDuplicate = userRepository.findByUsername(user.getUsername());
			if (usernameDuplicate != null) {
				result.rejectValue("username", "Duplicate.user.username");
				return "protected/userAdd";
			}
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
			String coded = encoder.encode(user.getPassword());
			user.setPassword(coded);
		} else {

		}
		Collection<Roles> roleList = user.getRoles();
		List<Long> idList = new ArrayList<>();
		for (Roles role : roleList) {
			Long roleId = Long.valueOf(role.getName());
			idList.add(roleId);
		}
		List<Roles> foundRoles = roleRepository.findListForIds(idList);
		user.setRoles(foundRoles);

		userRepository.save(user);
		return "redirect:/userlist";
	}

	@RequestMapping("/deleteUser-{id}")
	public String deleteUser(Model model, @PathVariable Long id, Locale request) {
		String msg = new String();
		try {
			userRepository.delete(id);
			msg = messageSource.getMessage("Delete.user.success", null, "Deleted", request);
			model.addAttribute("messageSuccess", msg);
		} catch (DataIntegrityViolationException e) {
			msg = messageSource.getMessage("Delete.user.fail", null, "Deleted", request);
			model.addAttribute("messageFail", msg);
		}
		List<Users> userList = userRepository.findAll();
		model.addAttribute("list", userList);
		return "protected/userAdm";
	}

	@RequestMapping("/searchUser")
	public ModelAndView searchUser(@ModelAttribute("user") Users user, BindingResult result) {
		ModelAndView model = new ModelAndView("protected/userAdm");
		// List<Users> searchUsers = userRepository.searchUsers(user.getFirstName(),
		// user.getLastName(), user.getUsername(), user.getRole().getName());
		// model.addObject("list", searchUsers);
		return model;
	}

	@RequestMapping("/cancelUser")
	public String cancelUser() {
		return "redirect:/userlist";
	}

}
