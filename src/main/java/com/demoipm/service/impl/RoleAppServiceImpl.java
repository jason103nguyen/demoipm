package com.demoipm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoipm.dao.RoleAppDao;
import com.demoipm.dto.RoleAppDto;
import com.demoipm.entities.RoleApp;
import com.demoipm.service.RoleAppService;

@Service
@Transactional
public class RoleAppServiceImpl implements RoleAppService {

	@Autowired
	private RoleAppDao roleAppDao;
	
	@Override
	public void create(RoleAppDto roleAppDto) {

		RoleApp roleApp = new RoleApp(roleAppDto);
		roleAppDao.save(roleApp);
	}

	@Override
	public RoleAppDto readById(int id) throws Exception {

		Optional<RoleApp> roleApp = roleAppDao.findById(id);
		RoleAppDto roleAppDto = null;
		if (roleApp.isEmpty()) {
			throw new Exception("The id doesn't exists");
		} else {
			roleAppDto = new RoleAppDto(roleApp.get());
		}
		return roleAppDto;
	}

	@Override
	public List<RoleAppDto> readAll() throws Exception {

		List<RoleApp> listRoleApp = (List<RoleApp>) roleAppDao.findAll();
		List<RoleAppDto> listRoleAppDto = new ArrayList<RoleAppDto>();
		
		if (listRoleApp.isEmpty()) {
			
			throw new Exception("This list is empty");
		} else {
			
			for (RoleApp roleApp : listRoleApp) {
				RoleAppDto roleAppDto = new RoleAppDto(roleApp);
				listRoleAppDto.add(roleAppDto);
			}
		}
		
		return listRoleAppDto;
	}

	@Override
	public void update(RoleAppDto roleAppDto) {

		RoleApp roleApp = new RoleApp(roleAppDto);
		roleAppDao.save(roleApp);
	}

	@Override
	public void deleteById(int id) {

		if (roleAppDao.existsById(id)) {
			roleAppDao.deleteById(id);
		}
	}

}
