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
}
