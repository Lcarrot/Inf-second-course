package ru.itis.tyshenko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.tyshenko.dto.UserDto;
import ru.itis.tyshenko.form.UserForm;
import ru.itis.tyshenko.service.UserService;
import ru.itis.tyshenko.util.BindingResultMessages;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private BindingResultMessages bindingResultMessages;
    @Autowired
    private EntityController entityController;

    @GetMapping("/admin/signIn")
    public String getSignIn() {
        return "admin_signIn_page";
    }

    @PostMapping("/admin/signIn")
    public String checkSignIn(UserForm adminForm) {
        Optional<UserDto> adminDto = userService.authenticate(adminForm);
        if (adminDto.isPresent()) {
            return "cms_list";
        }
        return "redirect:/admin/cmsList";
    }

    @GetMapping("/admin/addUser")
    public String getPage(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "sign_up_page";
    }

    @PostMapping("/admin/addUser")
    public String addUser(HttpServletRequest request, @Valid UserForm user, BindingResult result, Model model) {
        java.util.Map<String, Object> map = Collections.singletonMap("userForm", user);
        return entityController.saveNewUser(request, user, userService, result, "error!", map, model,
                bindingResultMessages, "sign_up_page", "redirect:/admin/users?page=0");
    }

    @GetMapping("/admin/users")
    public String getUsers(@RequestParam(value = "page", required = false) Integer page, Model model) {
        if (page == null || page <= 0) page = 1;
        else {
            int max_page = userService.getMaxPage();
            page = (page > max_page) ? max_page : page;
        }
        int previous_page = (page == 1) ? 1 : page - 1;
        model.addAttribute("pre_page", previous_page);
        model.addAttribute("page", page);
        model.addAttribute("next_page", page + 1);
        model.addAttribute("users", userService.getAll(page));
        return "admin_users";
    }
}
