package org.security.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.data.persistance.model.Privilege;
import org.data.persistance.repository.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PrivilegeController {

	@Autowired
	private PrivilegeRepository privRepo;

	@Autowired
	MessageSource messageSource;

	@RequestMapping("privlist")
	public ModelAndView viewPrivilege() {
		ModelAndView model = new ModelAndView("protected/privilegeAdm");
		List<Privilege> privlist = privRepo.findAll();
		model.addObject("list", privlist);
		model.addObject(new Privilege());
		return model;
	}

	@RequestMapping("/addPrivilege")
	public ModelAndView addPrivilege() {
		ModelAndView model = new ModelAndView("protected/privilegeAdd");
		model.addObject("priv", new Privilege());
		return model;
	}

	@RequestMapping("/editPrivilege-{id}")
	public ModelAndView editPrivilege(@PathVariable Long id) {
		ModelAndView model = new ModelAndView("protected/privilegeAdd");
		Privilege priv = privRepo.findOne(id);
		model.addObject("priv", priv);
		return model;
	}

	@RequestMapping("/savePrivilege")
	public String savePrivilege(@Valid @ModelAttribute("priv") Privilege privilege, BindingResult result, ModelAndView model) {

		if (result.hasErrors()) {
			return "protected/privilegeAdd";
		}

		if (privilege.getId() == null) {
			Privilege nameDuplicate = privRepo.findByName(privilege.getName());
			if (nameDuplicate != null) {
				result.rejectValue("name", "Duplicate.privilege.name");
				return "protected/userAdd";
			}
		}

		privRepo.save(privilege);
		return "redirect:/privlist";
	}

	@RequestMapping("/deletePrivilege-{id}")
	public String deletePrivilege(Model model, @PathVariable Long id, Locale request) {
		String msg = new String();
		try {
			privRepo.delete(id);
			msg = messageSource.getMessage("Delete.priv.success", null, "Deleted", request);
			model.addAttribute("messageSuccess", msg);
		} catch (DataIntegrityViolationException e) {
			msg = messageSource.getMessage("Delete.priv.fail", null, "Deleted", request);
			model.addAttribute("messageFail", msg);
		}
		List<Privilege> privList = privRepo.findAll();
		model.addAttribute("list", privList);
		return "protected/privilegeAdm";
	}

//	@RequestMapping("/searchPrivilege")
//	public ModelAndView searchUser(@ModelAttribute("user") Users user, BindingResult result) {
//		ModelAndView model = new ModelAndView("protected/userAdm");
//		// List<Users> searchUsers = userRepository.searchUsers(user.getFirstName(),
//		// user.getLastName(), user.getUsername(), user.getRole().getName());
//		// model.addObject("list", searchUsers);
//		return model;
//	}

	@RequestMapping("/cancelPrivilege")
	public String cancelPrivilege() {
		return "redirect:/privlist";
	}
}
