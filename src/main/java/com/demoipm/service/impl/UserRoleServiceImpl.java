package com.demoipm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoipm.dao.UserRoleDao;
import com.demoipm.dto.UserRoleDto;
import com.demoipm.entities.UserRole;
import com.demoipm.service.UserRoleService;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleDao userRoleDao;
	
	@Override
	public void create(UserRoleDto userRoleDto) {

		UserRole userRole = new UserRole(userRoleDto);
		userRoleDao.save(userRole);
	}

	@Override
	public UserRoleDto readById(int id) throws Exception {

		Optional<UserRole> userRole = userRoleDao.findById(id);
		UserRoleDto userRoleDto = null;
		if (!userRole.isPresent()) {
			throw new Exception("The id doesn't exists");
		} else {
			userRoleDto = new UserRoleDto(userRole.get());
		}
		return userRoleDto;
	}

	@Override
	public List<UserRoleDto> readAll() throws Exception {

		List<UserRole> listUserRole = (List<UserRole>) userRoleDao.findAll();
		List<UserRoleDto> listUserRoleDto = new ArrayList<UserRoleDto>();
		
		if (listUserRole.isEmpty()) {
			
			throw new Exception("This list is empty");
		} else {
			
			for (UserRole userRole : listUserRole) {
				UserRoleDto userRoleDto = new UserRoleDto(userRole);
				listUserRoleDto.add(userRoleDto);
			}
		}
		
		return listUserRoleDto;
	}

	@Override
	public void update(UserRoleDto userRoleDto) {

		UserRole userRole = new UserRole(userRoleDto);
		userRoleDao.save(userRole);
	}

	@Override
	public void deleteById(int id) {

		if (userRoleDao.existsById(id)) {
			userRoleDao.deleteById(id);
		}
	}

}
