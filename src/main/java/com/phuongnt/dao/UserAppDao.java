package com.phuongnt.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.phuongnt.entities.UserApp;

public interface UserAppDao extends CrudRepository<UserApp, Integer>{

	@Query("SELECT u FROM UserApp u WHERE u.username LIKE :username")
	UserApp findUsername(@Param(value = "username") String username);
}
