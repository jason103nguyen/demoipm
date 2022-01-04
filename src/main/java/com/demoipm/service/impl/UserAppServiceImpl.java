package com.demoipm.service.impl;

import com.demoipm.consts.MessageConst;
import com.demoipm.dao.RoleAppDao;
import com.demoipm.dao.UserAppDao;
import com.demoipm.dao.UserRoleDao;
import com.demoipm.dto.UserAppDto;
import com.demoipm.dto.general.ResponseDto;
import com.demoipm.dto.usermanage.UserCreateRequestDto;
import com.demoipm.dto.usermanage.UserListPageResponseDto;
import com.demoipm.dto.usermanage.UserResponseDto;
import com.demoipm.dto.usermanage.UserUpdateRequestDto;
import com.demoipm.entities.RoleApp;
import com.demoipm.entities.UserApp;
import com.demoipm.entities.UserRole;
import com.demoipm.service.UserAppService;
import com.demoipm.utils.EncrytedPasswordUtils;
import org.apache.commons.lang3.StringUtils;
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

    /**
     * Read user by id
     * @param id
     * @return
     * @throws Exception
     */
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

    /**
     * Read all user from database
     * @return
     * @throws Exception
     */
    @Override
    public List<UserAppDto> readAll() throws Exception {

        List<UserApp> listUserApp = userAppDao.findAll();
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

    /**
     * Update user
     * @param userAppDto
     */
    @Override
    public void update(UserAppDto userAppDto) {

        UserApp userApp = new UserApp(userAppDto);
        userAppDao.save(userApp);
    }

    /**
     * Delete user by id
     * @param id
     */
    @Override
    public void deleteById(int id) {

        if (userAppDao.existsById(id)) {
            userAppDao.deleteById(id);
        }
    }

    /**
     * Get user detail from username
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
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

    /**
     * Get user list according to condition (search ,page number, entries number)
     * @param searchWord
     * @param pageNo
     * @param entriesNo
     * @return
     */
    @Override
    public UserListPageResponseDto readByCondition(String searchWord, int pageNo, int entriesNo) {
        LOGGER.info("Start readByCondition with searchWord {}, pageNo {}, entriesNo {}", searchWord, pageNo, entriesNo);
        UserListPageResponseDto responseDto = new UserListPageResponseDto();
        try {
            // Get data from database with pagination and search word
            Pageable pageable = PageRequest.of(pageNo - 1, entriesNo, Sort.Direction.ASC, "username");
            Page<UserApp> userPage = null;
            if (StringUtils.isBlank(searchWord)) {
                userPage = userAppDao.findAll(pageable);
            } else {
                userPage = userAppDao.fullTextSearchByCondition(searchWord, pageable);
            }

            // Prepare response dto
            responseDto.setSearchWord(searchWord);
            responseDto.setCurrentPage(pageNo);
            responseDto.setEntriesNo(entriesNo);
            responseDto.setTotalPage(userPage.getTotalPages());
            responseDto.setTotalEntries(userPage.getTotalElements());
            List<UserResponseDto> userList = userPage.getContent().stream().map(userApp -> {
                UserResponseDto userDto = new UserResponseDto();
                userDto.setEmail(userApp.getEmail());
                userDto.setFullName(userApp.getFullName());
                userDto.setPhone(userApp.getPhone());
                userDto.setUsername(userApp.getUsername());
                List<String> roles = userApp.getListUserRole().stream()
                        .map(UserRole::getRoleApp)
                        .map(RoleApp::getRoleName)
                        .collect(Collectors.toList());
                userDto.setRoles(roles);
                return userDto;
            }).collect(Collectors.toList());
            responseDto.setUserList(userList);
        } catch (Throwable t) {
            LOGGER.error("Has error when readByCondition", t);
            responseDto.setError(true);
            responseDto.setMessage(MessageConst.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.info("End readByCondition with searchWord {}, pageNo {}, entriesNo {}", searchWord, pageNo, entriesNo);
            return responseDto;
        }
    }

    /**
     * Create new user
     * @param requestDto
     * @param responseDto
     */
    @Override
    public void createNewUser(UserCreateRequestDto requestDto, ResponseDto responseDto) {
        LOGGER.info("Start createNewUser with {}", requestDto);
        try {
            // Mapping dto to entity
            UserApp entity = new UserApp();
            entity.setFullName(requestDto.getFullName());
            entity.setEmail(requestDto.getEmail());
            entity.setPhone(requestDto.getPhone());
            entity.setUsername(requestDto.getUsername());
            entity.setPassword(EncrytedPasswordUtils.encrytePassword(requestDto.getPassword()));

            // Get and set role by request
            List<RoleApp> roleAppEntities = roleAppDao.getAllByRoleNameIn(requestDto.getRoles());
            List<UserRole> userRoles = roleAppEntities.stream().map(roleApp -> {
                UserRole userRole = new UserRole();
                userRole.setUserApp(entity);
                userRole.setRoleApp(roleApp);
                return userRole;
            }).collect(Collectors.toList());

            userAppDao.save(entity);
            userRoleDao.saveAll(userRoles);
        } catch (Throwable t) {
            LOGGER.error("Has error when createNewUser", t);
            responseDto.setError(true);
            responseDto.setMessage(MessageConst.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.info("End createNewUser with {}", requestDto);
        }
    }

    /**
     * Read user by username
     * @param username
     * @param responseDto
     * @return
     */
    @Override
    public UserUpdateRequestDto readUserByUsername(String username, ResponseDto responseDto) {
        LOGGER.info("Start readUserByUsername with {}", username);
        UserUpdateRequestDto userDto = new UserUpdateRequestDto();
        try {
            UserApp userApp = userAppDao.findUsername(username);
            if (userApp != null) {
                userDto.setEmail(userApp.getEmail());
                userDto.setFullName(userApp.getFullName());
                userDto.setPhone(userApp.getPhone());
                userDto.setUsername(userApp.getUsername());
                List<String> roles = userApp.getListUserRole().stream()
                        .map(UserRole::getRoleApp)
                        .map(RoleApp::getRoleName)
                        .collect(Collectors.toList());
                userDto.setRoles(roles);
            } else {
                responseDto.setError(true);
                responseDto.setMessage(MessageConst.USERNAME_NOT_EXISTED);
            }
        } catch (Throwable t) {
            LOGGER.error("Has error when readUserByUsername", t);
            responseDto.setError(true);
            responseDto.setMessage(MessageConst.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info("End readUserByUsername with {}", username);
        return userDto;
    }

    /**
     * Update existed user
     * @param requestDto
     * @param responseDto
     */
    @Override
    public void updateExistedUser(UserUpdateRequestDto requestDto, ResponseDto responseDto) {
        LOGGER.info("Start updateExistedUser with {}", requestDto);
        try {
            // Mapping dto to entity
            UserApp entity = userAppDao.findUsername(requestDto.getUsername());
            if (entity == null) {
                LOGGER.error("User name not existed {}", requestDto.getUsername());
                responseDto.setError(true);
                responseDto.setMessage(MessageConst.USERNAME_NOT_EXISTED);
                return;
            }
            entity.setFullName(requestDto.getFullName());
            entity.setEmail(requestDto.getEmail());
            entity.setPhone(requestDto.getPhone());
            entity.setUsername(requestDto.getUsername());
            userAppDao.save(entity);

            List<RoleApp> allRoles = roleAppDao.findAll();
            List<UserRole> userRolesToAdd = getListUserRolesToAdd(requestDto, entity, allRoles);
            List<UserRole> userRolesToRemove = getListUserRolesToRemove(requestDto, entity, allRoles);
            userRoleDao.saveAll(userRolesToAdd);
            userRoleDao.deleteAll(userRolesToRemove);

        } catch (Throwable t) {
            LOGGER.error("Has error when updateExistedUser", t);
            responseDto.setError(true);
            responseDto.setMessage(MessageConst.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.info("End updateExistedUser with {}", requestDto);
        }
    }

    /**
     * Delete user by username
     * @param username
     */
    @Override
    public void deleteUserByUsername(String username) {
        LOGGER.info("Start deleteUserByUsername with {}", username);
        try {
            UserApp userApp = userAppDao.findUsername(username);
            if (userApp != null) {
                userRoleDao.deleteAll(userApp.getListUserRole());
                userAppDao.delete(userApp);
            }
        } catch (Throwable t) {
            LOGGER.error("Has error deleteUserByUsername", t);
        }
        LOGGER.info("End deleteUserByUsername with {}", username);
    }

    /**
     * Get list user role to remove
     * @param requestDto
     * @param entity
     * @param allRoles
     * @return
     */
    private List<UserRole> getListUserRolesToRemove(UserUpdateRequestDto requestDto, UserApp entity, List<RoleApp> allRoles) {
        List<String> existedRoles = entity.getListUserRole().stream()
                .map(UserRole::getRoleApp)
                .map(RoleApp::getRoleName)
                .collect(Collectors.toList());
        List<String> requestRoles = requestDto.getRoles();

        List<String> rolesToRemove = allRoles.stream()
                .filter(roleApp ->
                        !requestRoles.contains(roleApp.getRoleName()) && existedRoles.contains(roleApp.getRoleName()))
                .map(RoleApp::getRoleName)
                .collect(Collectors.toList());

        List<UserRole> userRoles = entity.getListUserRole().stream()
                .filter(userRoleDao -> rolesToRemove.contains(userRoleDao.getRoleApp().getRoleName()))
                .collect(Collectors.toList());

        return userRoles;
    }

    /**
     * Get list user role to add
     * @param requestDto
     * @param entity
     * @param allRoles
     * @return
     */
    private List<UserRole> getListUserRolesToAdd(UserUpdateRequestDto requestDto, UserApp entity, List<RoleApp> allRoles) {
        List<String> existedRoles = entity.getListUserRole().stream()
                .map(UserRole::getRoleApp)
                .map(RoleApp::getRoleName)
                .collect(Collectors.toList());
        List<String> requestRoles = requestDto.getRoles();

        List<UserRole> userRoles = allRoles.stream()
                .filter(roleApp ->
                        requestRoles.contains(roleApp.getRoleName()) && !existedRoles.contains(roleApp.getRoleName()))
                .map(roleApp -> {
                    UserRole userRole = new UserRole();
                    userRole.setUserApp(entity);
                    userRole.setRoleApp(roleApp);
                    return userRole;
                }).collect(Collectors.toList());

        return userRoles;
    }
}
