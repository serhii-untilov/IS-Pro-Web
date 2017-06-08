package ua.in.usv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.in.usv.entity.root.CustomUser;
import ua.in.usv.service.UserService;

@Controller
public class StaffController {

    private final UserService userService;

    @Autowired
    public StaffController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/staff/index")
    public String index(Model model) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        CustomUser customUser = userService.findByLogin(login);

        model.addAttribute("login", login);
        model.addAttribute("name", customUser.getName());

        return "staff/index";
    }
}
