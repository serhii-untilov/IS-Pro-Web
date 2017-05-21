package ua.in.usv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.in.usv.entity.CustomUser;
import ua.in.usv.service.UserService;

@Controller
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Boolean logout;

    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    public String loginPage(@RequestParam(required = false) Boolean logout, Model model) {
        logger.info("loginPageConroller");

        CustomUser customUser = userService.findByLogin("usv");

        model.addAttribute("logout", logout);
        return "login";
    }
}
