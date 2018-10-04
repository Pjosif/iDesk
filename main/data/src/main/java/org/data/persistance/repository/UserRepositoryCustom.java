package org.data.persistance.repository;

import java.util.List;

import org.data.persistance.model.Users;

public interface UserRepositoryCustom {

	List<Users> searchUsers(String firstName,String lastName, String username, String role);
}
