package com.demoipm.dao;

import com.demoipm.entities.UserApp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserAppDao extends CrudRepository<UserApp, Integer> {

    @Query("SELECT u FROM UserApp u WHERE u.username LIKE :username")
    UserApp findUsername(@Param(value = "username") String username);

    Page<UserApp> findAllByFullNameLikeOrPhoneLikeOrEmailLikeOrUsernameLike(String searchWord, Pageable pageable);
}
