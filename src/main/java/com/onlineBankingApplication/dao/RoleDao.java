package com.onlineBankingApplication.dao;

import org.springframework.data.repository.CrudRepository;

import com.onlineBankingApplication.domain.security.entity.Role;

public interface RoleDao extends CrudRepository<Role,Integer>{
	Role findByName(String name);

}
