package com.demoipm.controller;

import com.demoipm.dto.usermanage.UserListPageResponseDto;
import com.demoipm.service.UserAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserManageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserManageController.class);

    @Autowired
    private UserAppService userAppService;

    @Secured("ROLE_ADMIN")
    @RequestMapping("/manage-user")
    public String getUserList(@RequestParam("searchWord") String searchWord,
                              @RequestParam("pageNo") int pageNo,
                              @RequestParam("entriesNo") int entriesNo,
                              Model model) {
        LOGGER.info("Start get user list with search word {}, page {}, entries no {}", searchWord, pageNo, entriesNo);
        UserListPageResponseDto responseDto = userAppService.readByCondition(searchWord, pageNo, entriesNo);
        model.addAttribute("response", responseDto);
        LOGGER.info("End get user list with search word {}, page {}, entries no {}", searchWord, pageNo, entriesNo);
        return "/manage-user";
    }
}
