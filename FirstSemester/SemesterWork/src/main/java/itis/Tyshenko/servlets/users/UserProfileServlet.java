package itis.Tyshenko.servlets.users;

import itis.Tyshenko.dto.UserDTO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static itis.Tyshenko.utility.PreparedRequestTemplateForEntity.setRequestAttributeForUser;

@WebServlet(name = "UserProfile", value="/service/profile")
public class UserProfileServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO user = (UserDTO) req.getSession().getAttribute("user");
        setRequestAttributeForUser(req, user);
        req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
    }
}
