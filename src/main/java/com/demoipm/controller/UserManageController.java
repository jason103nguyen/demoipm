package com.demoipm.controller;

import com.demoipm.dto.RoleAppDto;
import com.demoipm.dto.general.ResponseDto;
import com.demoipm.dto.usermanage.UserCreateRequestDto;
import com.demoipm.dto.usermanage.UserListPageResponseDto;
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
        entriesNo = entriesNo != null ? entriesNo : 10;
        pageNo = pageNo != null ? pageNo : 1;
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
}
