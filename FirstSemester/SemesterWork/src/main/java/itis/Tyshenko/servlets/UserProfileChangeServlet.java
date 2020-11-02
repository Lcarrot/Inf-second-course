package itis.Tyshenko.servlets;
import itis.Tyshenko.dto.UserDTO;
import itis.Tyshenko.services.UserService;
import itis.Tyshenko.statuses.SignUpStatus;
import itis.Tyshenko.utility.PreparerMessageForUserData;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static itis.Tyshenko.utility.PreparedRequestTemplate.setRequestAttribute;
import static itis.Tyshenko.utility.UserDataCheckerRegistration.*;

@WebServlet(name = "ChangeUserData", value = "/service/profile/change")
public class UserProfileChangeServlet extends HttpServlet {

    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO user = (UserDTO) req.getSession().getAttribute("user");
        setRequestAttribute(req, user);
        req.getRequestDispatcher("/views/changeProfile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO user = (UserDTO) req.getSession().getAttribute("user");
        String password = req.getParameter("password");
        String new_password = req.getParameter("new_password");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        if (new_password != null && !new_password.equals("")) {
            if (checkPassword(password)) password = new_password;
        }
        setRequestAttribute(req, user);
        if (userService.equalsRowPasswordWithUserPassword(password, user.getPassword())) {
            List<SignUpStatus> statuses = changeProfile(user, email, country);
            if (new_password != null && !new_password.equals("")) {
                if (checkPassword(password)) password = new_password;
                else statuses.add(SignUpStatus.WRONG_PASSWORD);
            }
            if (statuses.get(0).equals(SignUpStatus.OK)) {
                req.getSession().setAttribute("user", user);
                userService.updateUser(user, password);
                // TODO: 10/31/2020 сделать cookie для входа на сайт;
                resp.sendRedirect(req.getContextPath() + "/service/profile");
            }
            else {
                Map<SignUpStatus, String> map = getDescription();
                String error = PreparerMessageForUserData.concatAllErrorsSignUpStatus(statuses, map).toString();
                req.setAttribute("description", error);
                req.getRequestDispatcher("/views/changeProfile.jsp").forward(req, resp);
            }
        }
        else {
            req.setAttribute("description", "This password doesn't match with yours");
            req.getRequestDispatcher("/views/changeProfile.jsp").forward(req, resp);
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        userService = (UserService) context.getAttribute("userService");
    }

    private List<SignUpStatus> changeProfile(UserDTO userDTO, String email, String country) {
        List<SignUpStatus> statuses = new LinkedList<>();
        if (checkEmail(email)) userDTO.setEmail(email);
        else statuses.add(SignUpStatus.WRONG_EMAIL);
        if (checkCountry(country)) {
            userDTO.setCountry(country);
        }
        if (statuses.isEmpty()) statuses.add(SignUpStatus.OK);
        return statuses;
    }

    private Map<SignUpStatus, String> getDescription() {
        Map<SignUpStatus, String> map = new HashMap<>();
        map.put(SignUpStatus.WRONG_EMAIL, "Email is incorrect");
        map.put(SignUpStatus.WRONG_PASSWORD, "Password is incorrect, please use latin letters(need no less then 1 uppercase and 1 lowercase)," +
                "number(no less then 1, and special chars(@#%$^), DON'T USE SPACE");
        return map;
    }
}
