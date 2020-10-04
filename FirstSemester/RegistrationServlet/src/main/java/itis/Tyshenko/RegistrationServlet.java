package itis.Tyshenko;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegistrationServlet extends HttpServlet {

    private final String[] PARAMETERS_NAME = new String[]{"name" , "email", "password", "country", "gender", "agree"};

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> parameters = getParameter(request);
        String confirmedPassword = request.getParameter("confirmed_password");
        UserDataChecker dataChecker = new UserDataChecker(parameters.get(PARAMETERS_NAME[0]), parameters.get(PARAMETERS_NAME[1]),
                PARAMETERS_NAME[2], PARAMETERS_NAME[3], PARAMETERS_NAME[4]);

        if (dataChecker.isValid() && PARAMETERS_NAME[5].equals("on") && dataChecker.checkPasswordEquals(confirmedPassword)) {
            String defaultGender = "male";
            User user = new User(parameters.get(PARAMETERS_NAME[0]), parameters.get(PARAMETERS_NAME[1]),
                    parameters.get(PARAMETERS_NAME[2]), parameters.get(PARAMETERS_NAME[3]).hashCode(), parameters.get(PARAMETERS_NAME[4]).equals(defaultGender));
            UserDataWriter.writeUserInCSV(user);
        }
        getServletContext().getRequestDispatcher("/registration.html").forward(request,response);
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
