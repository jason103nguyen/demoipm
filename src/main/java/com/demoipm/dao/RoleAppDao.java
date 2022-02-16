package com.demoipm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demoipm.entities.RoleApp;
import com.demoipm.entities.UserApp;

public interface RoleAppDao extends CrudRepository<RoleApp, Integer>{
	
	@Query("SELECT ur.roleApp.roleName FROM UserRole ur WHERE ur.userApp.id = :userId")
	public List<String> getRoleNames(int userId);

	public List<RoleApp> getAllByRoleNameIn(List<String> roleNames);

	List<RoleApp> findAll();
}
