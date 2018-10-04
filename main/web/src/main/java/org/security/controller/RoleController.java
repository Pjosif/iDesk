package org.security.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.data.persistance.model.Privilege;
import org.data.persistance.model.Roles;
import org.data.persistance.repository.PrivilegeRepository;
import org.data.persistance.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RoleController {

	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	PrivilegeRepository privRepo;

	@Autowired
	MessageSource messageSource;
	
	@ModelAttribute("allPrivileges")
	public List<Privilege> populatePrivileges(){
		ArrayList<Privilege> privList = (ArrayList<Privilege>) privRepo.findAll();
		return privList;
	}

	@RequestMapping("/rolelist")
	public ModelAndView viewRoles() {
		List<Roles> roleList = roleRepo.findAll();
		return new ModelAndView("protected/roleAdm", "list", roleList);
	}

	@RequestMapping("/addRole")
	public ModelAndView addRole() {
		ModelAndView model = new ModelAndView("protected/roleAdd");
		model.addObject("role", new Roles());
		return model;
	}

	@RequestMapping("/editRole-{id}")
	public ModelAndView editUser(@PathVariable Long id) {
		ModelAndView model = new ModelAndView("protected/roleAdd");
		Roles role = roleRepo.findOne(id);
		model.addObject("role", role);
		return model;
	}

	@RequestMapping(value = "/saveRole", method = RequestMethod.POST)
	public String saveRole(@Valid @ModelAttribute("role") Roles role, BindingResult result, ModelAndView model) {
		if (role.getPrivilieges().isEmpty()) {
			result.rejectValue("privilieges", "NotSelect.role.privilieges");
		}
		if (result.hasErrors()) {
			return "protected/roleAdd";
		}

		if (role.getId() == null) {
			Roles roleDuplicate = roleRepo.findByName(role.getName());
			if (roleDuplicate != null) {
				result.rejectValue("role", "Duplicate.role.role");
				return "protected/userAdd";
			}
		}
		Collection<Privilege> privList = role.getPrivilieges();
		List<Long> idList = new ArrayList<>();
		for (Privilege privilege : privList) {
			Long privId = Long.valueOf(privilege.getName());
			idList.add(privId);
		}
		List<Privilege> foundPrivs = privRepo.findListForIds(idList);
		role.setPrivilieges(foundPrivs);
		roleRepo.save(role);
		return "redirect:/rolelist";
	}

	@RequestMapping("/deleteRole-{id}")
	public String deleteRole(Model model, @PathVariable Long id, Locale request) {
		String msg = new String();
		try {
			roleRepo.delete(id);
			msg = messageSource.getMessage("Delete.role.success", null, "Deleted", request);
			model.addAttribute("messageSuccess", msg);
		} catch (DataIntegrityViolationException e) {
			msg = messageSource.getMessage("Delete.role.fail", null, "Fail to delete", request);
			model.addAttribute("messageFail",msg);
		}
		List<Roles> roleList = roleRepo.findAll();
		model.addAttribute("list", roleList);
		return "protected/roleAdm";
	}
	
	@RequestMapping("/cancelRole")
	public String cancelRole() {
		return "redirect:/rolelist";
	}
}
