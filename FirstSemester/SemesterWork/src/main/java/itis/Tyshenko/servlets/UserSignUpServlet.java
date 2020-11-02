package itis.Tyshenko.servlets;

import itis.Tyshenko.dto.UserDTO;
import itis.Tyshenko.services.UserService;
import itis.Tyshenko.utility.PreparerMessageForUserData;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import static itis.Tyshenko.utility.PreparedRequestTemplate.getUserDtoFromRequest;

@WebServlet(name = "SignUp", value = "/signUp")
public class UserSignUpServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        userService = (UserService) context.getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO userDTO = getUserDtoFromRequest(req);
        String password = req.getParameter("password");
        String confirm_password = req.getParameter("confirm_password");
        String agreed = req.getParameter("agree");
        boolean rememberMe = req.getParameter("remember-checkbox") != null;
        PreparerMessageForUserData preparer = new PreparerMessageForUserData(userDTO, password, confirm_password);
        HttpSession session= req.getSession();
        if (preparer.checkFields() || rememberMe) {
            session.setAttribute("authorized", "true");
            userService.addUser(userDTO, password);
            session.setAttribute("user", userDTO);
            //// TODO: 10/31/2020 сделать cookie для входа на сайт;
            resp.sendRedirect(req.getContextPath() + "/service/profile");
        }
        else {
            session.setAttribute("authorized", "false");
            String agreedMessage = "if you don't agree with our rules, you can't use our site =()";
            String error = preparer.getMessage(agreedMessage);
            req.setAttribute("description", error);
            req.getRequestDispatcher("/views/signUp.jsp").forward(req, resp);
        }
    }
}
