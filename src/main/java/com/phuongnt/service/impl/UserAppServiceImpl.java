package com.phuongnt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.phuongnt.dao.RoleAppDao;
import com.phuongnt.dao.UserAppDao;
import com.phuongnt.dao.UserRoleDao;
import com.phuongnt.dto.UserAppDto;
import com.phuongnt.entities.UserApp;
import com.phuongnt.service.UserAppService;

@Service
@Transactional
public class UserAppServiceImpl implements UserAppService, UserDetailsService {

	@Autowired
	private UserAppDao userAppDao;
	
	@Autowired
	private RoleAppDao roleAppDao;
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Override
	public void create(UserAppDto userAppDto) {

		UserApp userApp = new UserApp(userAppDto);
		userAppDao.save(userApp);
	}

	@Override
	public UserAppDto readById(int id) throws Exception {

		Optional<UserApp> userApp = userAppDao.findById(id);
		UserAppDto userAppDto = null;
		if (userApp.isEmpty()) {
			throw new Exception("The id doesn't exists");
		} else {
			userAppDto = new UserAppDto(userApp.get());
		}
		return userAppDto;
	}

	@Override
	public List<UserAppDto> readAll() throws Exception {

		List<UserApp> listUserApp = (List<UserApp>) userAppDao.findAll();
		List<UserAppDto> listUserAppDto = new ArrayList<UserAppDto>();
		
		if (listUserApp.isEmpty()) {
			
			throw new Exception("This list is empty");
		} else {
			
			for (UserApp userApp : listUserApp) {
				UserAppDto userAppDto = new UserAppDto(userApp);
				listUserAppDto.add(userAppDto);
			}
		}
		
		return listUserAppDto;
	}

	@Override
	public void update(UserAppDto userAppDto) {

		UserApp userApp = new UserApp(userAppDto);
		userAppDao.save(userApp);
	}

	@Override
	public void deleteById(int id) {

		if (userAppDao.existsById(id)) {
			userAppDao.deleteById(id);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
        UserApp userApp = userAppDao.findUsername(username);

        if (userApp == null) {
            System.out.println("User not found! " + username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }

        List<String> roleNames = roleAppDao.getRoleNames(userApp.getId());

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new User(userApp.getUsername(),
                userApp.getPassword(), grantList);

        return userDetails;
	}

}
