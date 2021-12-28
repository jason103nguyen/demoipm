package com.demoipm.controller;

import com.demoipm.consts.MessageConst;
import com.demoipm.consts.URLConst;
import com.demoipm.consts.ViewConst;
import com.demoipm.dto.RoleAppDto;
import com.demoipm.dto.general.ResponseDto;
import com.demoipm.dto.usermanage.UserCreateRequestDto;
import com.demoipm.dto.usermanage.UserListPageResponseDto;
import com.demoipm.dto.usermanage.UserUpdateRequestDto;
import com.demoipm.service.RoleAppService;
import com.demoipm.service.UserAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserManageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserManageController.class);

    @Autowired
    private UserAppService userAppService;

    @Autowired
    private RoleAppService roleAppService;

    @Secured("ROLE_ADMIN")
    @RequestMapping(URLConst.MANAGE_USER_URL)
    public String getUserList(@RequestParam(value = "searchWord", required = false) String searchWord,
                              @RequestParam(value = "pageNo", required = false) Integer pageNo,
                              @RequestParam(value = "entriesNo", required = false) Integer entriesNo,
                              Model model) {
        LOGGER.info("Start get user list with search word {}, page {}, entries no {}", searchWord, pageNo, entriesNo);
        entriesNo = setDefaultEntriesNo(entriesNo);
        pageNo = setDefaultPageNo(pageNo);
        searchWord = setDefaultSearchWord(searchWord);
        UserListPageResponseDto responseDto = userAppService.readByCondition(searchWord, pageNo, entriesNo);
        model.addAttribute("response", responseDto);
        LOGGER.info("End get user list with search word {}, page {}, entries no {}", searchWord, pageNo, entriesNo);
        return ViewConst.MANAGE_USER_PAGE;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(URLConst.CREATE_USER_PAGE_URL)
    public String createUserPage(Model model) {
        LOGGER.info("Start show create user page");
        UserCreateRequestDto user = new UserCreateRequestDto();
        model.addAttribute("user", user);
        List<String> roles = getAllRoleNames();
        model.addAttribute("roles", roles);
        return ViewConst.CREATE_USER_PAGE;
    }

    @Secured("ROLE_ADMIN")
    @PostMapping(URLConst.PROCESS_CREATE_USER_URL)
    public String processCreateUser(@Valid @ModelAttribute("user") UserCreateRequestDto userCreateRequestDto,
                                    BindingResult result,
                                    Model model) {
        LOGGER.info("Start process create user {}", userCreateRequestDto);
        if (result.hasErrors()) {
            List<String> roles = getAllRoleNames();
            model.addAttribute("roles", roles);
            return ViewConst.CREATE_USER_PAGE;
        }

        ResponseDto responseDto = new ResponseDto();
        userAppService.createNewUser(userCreateRequestDto, responseDto);
        if (responseDto.hasError()) {
            List<String> roles = getAllRoleNames();
            model.addAttribute("roles", roles);
            model.addAttribute("response", responseDto);
            return ViewConst.CREATE_USER_PAGE;
        }
        return URLConst.REDIRECT + URLConst.MANAGE_USER_URL;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(URLConst.UPDATE_USER_PAGE_URL)
    public String updateUserPage(@RequestParam(value = "username", required = false) String username,
                                 Model model) {
        LOGGER.info("Start show update user page");
        ResponseDto responseDto = new ResponseDto();
        if (username == null) {
            responseDto.setError(true);
            responseDto.setMessage(MessageConst.USERNAME_REQUIRED);
            model.addAttribute("response", responseDto);
            return ViewConst.UPDATE_USER_PAGE;
        }

        UserUpdateRequestDto user = userAppService.readUserByUsername(username, responseDto);
        model.addAttribute("user", user);
        if (responseDto.hasError()) {
            model.addAttribute("response", responseDto);
        }
        List<String> roles = getAllRoleNames();
        model.addAttribute("roles", roles);
        return ViewConst.UPDATE_USER_PAGE;
    }

    @Secured("ROLE_ADMIN")
    @PostMapping(URLConst.PROCESS_UPDATE_USER_URL)
    public String processUpdateUser(@Valid @ModelAttribute("user") UserUpdateRequestDto userUpdateRequestDto,
                                    BindingResult result,
                                    Model model) {
        LOGGER.info("Start process update user {}", userUpdateRequestDto);
        if (result.hasErrors()) {
            List<String> roles = getAllRoleNames();
            model.addAttribute("roles", roles);
            return ViewConst.UPDATE_USER_PAGE;
        }

        ResponseDto responseDto = new ResponseDto();
        userAppService.updateExistedUser(userUpdateRequestDto, responseDto);
        if (responseDto.hasError()) {
            List<String> roles = getAllRoleNames();
            model.addAttribute("roles", roles);
            model.addAttribute("response", responseDto);
            return ViewConst.UPDATE_USER_PAGE;
        }
        return URLConst.REDIRECT + URLConst.MANAGE_USER_URL;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(URLConst.DELETE_USER_URL)
    public String deleteUser(@RequestParam("username") String username) {
        LOGGER.info("Start deleteUser with username {}", username);
        userAppService.deleteUserByUsername(username);
        LOGGER.info("End deleteUser with username {}", username);
        return URLConst.REDIRECT + URLConst.MANAGE_USER_URL;
    }

    private String setDefaultSearchWord(String searchWord) {
        if (searchWord != null) {
            return searchWord;
        }
        return "";
    }

    private Integer setDefaultPageNo(Integer pageNo) {
        if (pageNo == null || pageNo <= 0) {
            return 1;
        }
        return pageNo;
    }

    private Integer setDefaultEntriesNo(Integer entriesNo) {
        if (entriesNo == null || entriesNo <= 0) {
            return 10;
        }
        return entriesNo;
    }

    private List<String> getAllRoleNames() {
        List<RoleAppDto> roleList = new ArrayList<>();
        try {
            roleList = roleAppService.readAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roleList.stream().map(RoleAppDto::getRoleName).collect(Collectors.toList());
    }

}
