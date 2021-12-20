package com.demoipm.service.impl;

import com.demoipm.consts.MessageConst;
import com.demoipm.dao.RoleAppDao;
import com.demoipm.dao.UserAppDao;
import com.demoipm.dao.UserRoleDao;
import com.demoipm.dto.UserAppDto;
import com.demoipm.dto.usermanage.UserListPageResponseDto;
import com.demoipm.dto.usermanage.UserResponseDto;
import com.demoipm.entities.UserApp;
import com.demoipm.service.UserAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserAppServiceImpl implements UserAppService, UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAppService.class);

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
        if (!userApp.isPresent()) {
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

    @Override
    public UserListPageResponseDto readByCondition(String searchWord, int pageNo, int entriesNo) {
        LOGGER.info("Start readByCondition with searchWord {}, pageNo {}, entriesNo {}", searchWord, pageNo, entriesNo);
        UserListPageResponseDto responseDto = new UserListPageResponseDto();
        try {
            // Get data from database with pagination and search word
            Pageable pageable = PageRequest.of(pageNo - 1, entriesNo, Sort.Direction.ASC, "username");
            Page<UserApp> userPage = userAppDao.findAllByFullNameLikeOrPhoneLikeOrEmailLikeOrUsernameLike("%" + searchWord + "%", pageable);

            // Prepare response dto
            responseDto.setCurrentPage(pageNo - 1);
            responseDto.setEntriesNo(entriesNo);
            responseDto.setTotalPage(userPage.getTotalPages());
            responseDto.setTotalEntries(userPage.getTotalElements());
            responseDto.setUserList(userPage.getContent().stream().map(userApp -> {
                UserResponseDto userDto = new UserResponseDto();
                userDto.setEmail(userApp.getEmail());
                userDto.setFullName(userApp.getFullName());
                userDto.setPhone(userApp.getPhone());
                userDto.setUsername(userApp.getUsername());
                List<String> roles = userApp.getListUserRole().stream().map(userRole -> {
                    return userRole.getRoleApp().getRoleName();
                }).collect(Collectors.toList());
                userDto.setRoles(roles);
                return userDto;
            }).collect(Collectors.toList()));
        } catch (Throwable t) {
            LOGGER.error("Has error when readByCondition", t);
            responseDto.setError(true);
            responseDto.setMessage(MessageConst.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.info("End readByCondition with searchWord {}, pageNo {}, entriesNo {}", searchWord, pageNo, entriesNo);
            return responseDto;
        }
    }
}
