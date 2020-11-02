package itis.Tyshenko.utility;

import itis.Tyshenko.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;

public class PreparedRequestTemplate {

    public static void setRequestAttribute(HttpServletRequest request, UserDTO user) {
        System.out.println(user.toString());
        request.setAttribute("login", user.getLogin());
        request.setAttribute("email", user.getEmail());
        request.setAttribute("gender", user.getGender());
        request.setAttribute("country", user.getCountry());
    }

    public static UserDTO getUserDtoFromRequest(HttpServletRequest request) {
        return UserDTO.builder().login(request.getParameter("login")).email(request.getParameter("email")).country(request.getParameter("country")).
                gender(request.getParameter("gender")).build();
    }
}
