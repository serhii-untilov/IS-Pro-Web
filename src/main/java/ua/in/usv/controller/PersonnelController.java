package ua.in.usv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.in.usv.entity.firm.Company;
import ua.in.usv.entity.root.CustomUser;
import ua.in.usv.service.CompanyService;
import ua.in.usv.service.UserService;

@Controller
public class PersonnelController {

    @Value("${ispro.firm.site}")
    private String companySite;

    private final UserService userService;
    private final CompanyService companyService;

    @Autowired
    public PersonnelController(UserService userService, CompanyService companyService) {
        this.userService = userService;
        this.companyService = companyService;
    }

    @RequestMapping("/personnel/index")
    public String index(Model model) {
        setModelUser(model);
        setModelCompany(model);
        model.addAttribute("page_index_active", "active");
        return "personnel/index";
    }

    @RequestMapping("/personnel/personnel-list")
    public String list(Model model) {
        setModelUser(model);
        setModelCompany(model);
        model.addAttribute("page_personnel_list_active", "active");
        return "personnel/personnel-list";
    }

    private void setModelUser(Model model) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        CustomUser customUser = userService.findByLogin(login);
        model.addAttribute("user_name", customUser.getName());
    }

    private void setModelCompany(Model model) {
        Company company = companyService.findFirst();
        model.addAttribute("company_name", company.getName());
        model.addAttribute("company_site", companySite);
    }
}
