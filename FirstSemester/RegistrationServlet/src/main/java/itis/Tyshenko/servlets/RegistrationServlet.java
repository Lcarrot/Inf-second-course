package itis.Tyshenko.servlets;

import itis.Tyshenko.entity.User;
import itis.Tyshenko.utility.UserDataChecker;
import itis.Tyshenko.utility.UserDataWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "RegistrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {

    protected final String[] PARAMETERS_NAME = new String[]{"name" , "email", "password", "country", "gender", "agree"};

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Map<String, String> parameters = getParameter(request);

        String confirmedPassword = request.getParameter("confirmed_password");

        UserDataChecker dataChecker = new UserDataChecker(parameters.get("name"), parameters.get("email"),
                parameters.get("country"), parameters.get("password"), parameters.get("gender"));

        if (dataChecker.isValid() && request.getParameter("agree").equals("on") && dataChecker.checkPasswordEquals(confirmedPassword)) {
            String defaultGender = "male";
            User user = new User(parameters.get("name"), parameters.get("email"),
                    parameters.get("country"), parameters.get("password").hashCode(), parameters.get("gender").equals(defaultGender));

            UserDataWriter.writeUserInCSV(user);
            response.sendRedirect(request.getContextPath() + "/servlet");
        }
        else {
            doGet(request, response);
        }
    }

    private Map<String, String> getParameter(HttpServletRequest request) {
        Map<String, String> parameters = new HashMap<>();
        for (String parameter: PARAMETERS_NAME) {
            parameters.put(parameter, request.getParameter(parameter));
        }
        return parameters;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/registration.html").forward(request,response);
    }
}
