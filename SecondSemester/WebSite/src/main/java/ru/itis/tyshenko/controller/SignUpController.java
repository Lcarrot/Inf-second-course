package ru.itis.tyshenko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.tyshenko.form.UserForm;
import ru.itis.tyshenko.service.UserService;
import ru.itis.tyshenko.util.BindingResultMessages;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collections;

@Controller
public class SignUpController {

    private final UserService userService;
    private final BindingResultMessages bindingResultMessages;
    private final EntityController entityController;

    public SignUpController(UserService userService, BindingResultMessages bindingResultMessages, EntityController entityController) {
        this.userService = userService;
        this.bindingResultMessages = bindingResultMessages;
        this.entityController = entityController;
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String SignUpPage(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "sign_up_page";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String saveNewUser(HttpServletRequest request, @Valid UserForm user, BindingResult result, Model model) {
        java.util.Map<String, Object> map = Collections.singletonMap("userForm", user);
        return entityController.saveNewUser(request, user, userService, result, "error!", map, model,
                bindingResultMessages, "sign_up_page", "redirect:/profile");
    }
}
