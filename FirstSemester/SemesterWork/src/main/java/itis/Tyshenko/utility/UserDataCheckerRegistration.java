package itis.Tyshenko.utility;

import itis.Tyshenko.dto.UserDTO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDataCheckerRegistration {

    private final UserDTO userDTO;

    public UserDataCheckerRegistration(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+" +
            "(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    private final Pattern LOGIN_PATTERN = Pattern.compile("^[a-zA-Z0-9]{3,}$");

    public boolean checkLogin() {
        return LOGIN_PATTERN.matcher(userDTO.getLogin()).matches();
    }

    public boolean checkEmail() {
        return EMAIL_PATTERN.matcher(userDTO.getEmail()).matches();
    }

    public boolean checkPassword() {
        return PASSWORD_PATTERN.matcher(userDTO.getPassword()).matches();
    }

    public boolean checkCountry() {
        return userDTO.getCountry().length() > 0;
    }

    public boolean checkPasswordEquals(String confirmedPassword) {
        return userDTO.getPassword().equals(confirmedPassword);
    }

    public boolean checkGender() {
        String genderString = userDTO.getGender();
        return genderString != null && (genderString.equals("male") || genderString.equals("female"));
    }
}
