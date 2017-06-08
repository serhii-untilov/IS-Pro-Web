package ua.in.usv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Boolean logout;

    @RequestMapping("/login")
    public String loginPage(@RequestParam(required = false) Boolean logout, Model model) {
        logger.info("loginPageConroller");

        model.addAttribute("logout", logout);
        return "system/login";
    }
}
