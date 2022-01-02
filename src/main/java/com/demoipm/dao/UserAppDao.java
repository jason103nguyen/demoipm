package com.demoipm.dao;

import com.demoipm.entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserAppDao extends JpaRepository<UserApp, Integer>, UserAppCustomDao {

    @Query("SELECT u FROM UserApp u WHERE u.username LIKE :username")
    UserApp findUsername(@Param(value = "username") String username);

}
