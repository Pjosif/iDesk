package org.security.controller;

import java.beans.PropertyEditorSupport;

import org.data.persistance.model.Roles;
import org.data.persistance.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleEditor extends PropertyEditorSupport{

	@Autowired
	private RoleRepository roleRepo;
	
	@Override
	public void setAsText(String id) {
		Roles role = this.roleRepo.findOne(Long.valueOf(id));
		this.setValue(role);
	}
}
