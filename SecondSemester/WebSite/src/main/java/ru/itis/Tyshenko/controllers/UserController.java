package ru.itis.Tyshenko.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.Tyshenko.dto.UserDTO;
import ru.itis.Tyshenko.services.UserService;
import validators.UserValidator;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator validator;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getUsersPage(HttpServletRequest request, Model model) {
        model.addAttribute("user", userService.getById(((UserDTO) request.getSession().getAttribute("user")).id).get());
        return "profile";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String addUser(HttpServletRequest request, UserDTO user) {
        System.out.println("i'm here");
        if (validator.checkEmail(user.email)) {
            System.out.println("now i'm here");
            userService.add(user, user.password);
            request.getSession().setAttribute("user", user);
            return "profile";
        }
        else {
            return "redirect:/signUp";
        }
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String SignUpPage() {
        return "signUp";
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String SignInPage() {
        return "signIn";
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    @ResponseBody
    public String getUser(HttpServletRequest request, UserDTO userDTO) {
         userService.getByLogin(userDTO.login).ifPresent(user -> {
             if (userService.equalsRowPasswordWithUserPassword(user.password, userDTO.password)) {
                 request.getSession().setAttribute("user", user);
             }
        });
        return "profile";
    }
}
