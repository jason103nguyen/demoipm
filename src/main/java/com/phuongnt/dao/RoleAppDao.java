package com.phuongnt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.phuongnt.entities.RoleApp;
import com.phuongnt.entities.UserApp;

public interface RoleAppDao extends CrudRepository<RoleApp, Integer>{
	
	@Query("SELECT ur.roleApp.roleName FROM UserRole ur WHERE ur.userApp.id = :userId")
	public List<String> getRoleNames(int userId);
	
}
