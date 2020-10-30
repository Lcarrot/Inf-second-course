package itis.Tyshenko.servlets;

import itis.Tyshenko.dto.UserDTO;
import itis.Tyshenko.entity.User;
import itis.Tyshenko.services.UserService;
import itis.Tyshenko.utility.PreparerMessageForSignUp;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
        String confirm_password = req.getParameter("confirm_password");
        String agreed = req.getParameter("agree");
        PreparerMessageForSignUp preparer = new PreparerMessageForSignUp(userDTO, confirm_password, agreed);
        HttpSession session= req.getSession();
        if (preparer.checkFields()) {
            session.setAttribute("authorized", "true");
            User user = userService.addUser(userDTO);
            session.setAttribute("user", user);
            getServletContext().getRequestDispatcher("service/profile").forward(req, resp);
        }
        else {
            session.setAttribute("authorized", "false");
            String error = preparer.getMessage();
            req.setAttribute("description", error);
            req.getRequestDispatcher("/views/signUp.jsp").forward(req, resp);
        }
    }

    private UserDTO getUserDtoFromRequest(HttpServletRequest request) {
        return UserDTO.builder().login(request.getParameter("login")).email(request.getParameter("email")).country(request.getParameter("country")).
                gender(request.getParameter("gender")).password(request.getParameter("password")).build();
    }
}
