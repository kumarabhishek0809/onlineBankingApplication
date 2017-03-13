package com.onlineBankingApplication.dao;

import org.springframework.data.repository.CrudRepository;

import com.onlineBankingApplication.domain.Role;

public interface RoleDao extends CrudRepository<Role, Integer> {
	Role findByName(String name);

}
