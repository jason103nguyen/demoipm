package com.demoipm.dao;

import com.demoipm.entities.UserApp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserAppCustomDao {
    Page<UserApp> fullTextSearchByCondition(String searchWord, Pageable pageable);
}
