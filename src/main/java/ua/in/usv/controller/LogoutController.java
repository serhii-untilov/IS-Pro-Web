package ua.in.usv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping
    public String logoutPage() {
        logger.info("logoutPageConroller");
        return "redirect:/";
    }
}
