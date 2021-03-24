package ru.itis.tyshenko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.tyshenko.dto.UserDto;
import ru.itis.tyshenko.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SignInController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String SignInPage() {
        return "sign_in_page";
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public String getUser(HttpServletRequest request, UserDto userDTO) {
        return "profile";
    }
}
