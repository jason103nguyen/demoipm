package com.demoipm.controller;

import com.demoipm.consts.MessageConst;
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
    @RequestMapping("/manage-user")
    public String getUserList(@RequestParam(value = "searchWord", required = false) String searchWord,
                              @RequestParam(value = "pageNo", required = false) Integer pageNo,
                              @RequestParam(value = "entriesNo", required = false) Integer entriesNo,
                              Model model) {
        LOGGER.info("Start get user list with search word {}, page {}, entries no {}", searchWord, pageNo, entriesNo);
        entriesNo = entriesNo == null || entriesNo <= 0 ? 10 : entriesNo;
        pageNo = pageNo == null || pageNo <= 0 ? 1 : pageNo;
        searchWord = searchWord != null ? searchWord : "";
        UserListPageResponseDto responseDto = userAppService.readByCondition(searchWord, pageNo, entriesNo);
        model.addAttribute("response", responseDto);
        LOGGER.info("End get user list with search word {}, page {}, entries no {}", searchWord, pageNo, entriesNo);
        return "manageuser/manage-user";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/create-user-page")
    public String createUserPage(Model model) {
        LOGGER.info("Start show create user page");
        UserCreateRequestDto user = new UserCreateRequestDto();
        model.addAttribute("user", user);
        initRolesForUserModifyPage(model);
        return "manageuser/create-user";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/process-create-user")
    public String processCreateUser(@Valid @ModelAttribute("user") UserCreateRequestDto userCreateRequestDto,
                                    BindingResult result,
                                    Model model) {
        LOGGER.info("Start process create user {}", userCreateRequestDto);
        if (result.hasErrors()) {
            initRolesForUserModifyPage(model);
            return "manageuser/create-user";
        }

        ResponseDto responseDto = new ResponseDto();
        userAppService.createNewUser(userCreateRequestDto, responseDto);
        if (responseDto.hasError()) {
            initRolesForUserModifyPage(model);
            model.addAttribute("response", responseDto);
            return "manageuser/create-user";
        }
        return "redirect:manage-user";
    }

    private void initRolesForUserModifyPage(Model model) {
        List<RoleAppDto> roleList = new ArrayList<>();
        try {
            roleList = roleAppService.readAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> roles = roleList.stream().map(roleAppDto -> {
            return roleAppDto.getRoleName();
        }).collect(Collectors.toList());
        model.addAttribute("roles", roles);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/update-user-page")
    public String updateUserPage(@RequestParam(value = "username", required = false) String username,
                                 Model model) {
        LOGGER.info("Start show update user page");
        ResponseDto responseDto = new ResponseDto();
        if (username == null) {
            responseDto.setError(true);
            responseDto.setMessage(MessageConst.USERNAME_REQUIRED);
            model.addAttribute("response", responseDto);
            return "manageuser/update-user";
        }

        UserUpdateRequestDto user = userAppService.readUserByUsername(username, responseDto);
        model.addAttribute("user", user);
        if (responseDto.hasError()) {
            model.addAttribute("response", responseDto);
        }
        initRolesForUserModifyPage(model);
        return "manageuser/update-user";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/process-update-user")
    public String processUpdateUser(@Valid @ModelAttribute("user") UserUpdateRequestDto userUpdateRequestDto,
                                    BindingResult result,
                                    Model model) {
        LOGGER.info("Start process update user {}", userUpdateRequestDto);
        if (result.hasErrors()) {
            initRolesForUserModifyPage(model);
            return "manageuser/update-user";
        }

        ResponseDto responseDto = new ResponseDto();
        userAppService.updateExistedUser(userUpdateRequestDto, responseDto);
        if (responseDto.hasError()) {
            initRolesForUserModifyPage(model);
            model.addAttribute("response", responseDto);
            return "manageuser/update-user";
        }
        return "redirect:manage-user";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/delete-user")
    public String deleteUser(@RequestParam("username") String username) {
        LOGGER.info("Start deleteUser with username {}", username);
        userAppService.deleteUserByUsername(username);
        LOGGER.info("End deleteUser with username {}", username);
        return "redirect:manage-user";
    }
}
