package com.onlineBankingApplication.domain.security.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

@Entity
public class Role {

	private int roleId;
	private String name;
	
	private Set<UserRole> userRoles = new HashSet<UserRole>();
	
	public Role(){
		
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	
}
