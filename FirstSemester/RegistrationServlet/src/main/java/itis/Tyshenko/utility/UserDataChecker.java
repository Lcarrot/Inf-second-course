package itis.Tyshenko.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDataChecker {

    private final String name;
    private final String email;
    private final String country;
    private final String password;
    private final String gender;
   private final String EMAIL_REGEX = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);

    public UserDataChecker(String name, String email, String country, String password, String gender) {
        this.name = name;
        this.email = email;
        this.country = country;
        this.password = password.trim();
        this.gender = gender;
    }

    public boolean isValid() {
        return checkName() && checkEmail() && checkCountry() && checkPassword() && checkGender();
    }

    public boolean checkName() {
        return name.length() > 0;
    }

    public boolean checkEmail() {
        Matcher emailMatcher = emailPattern.matcher(email);
        return emailMatcher.matches();
    }

    public boolean checkPassword() {
        return password.length() > 3;
    }

    public boolean checkCountry() {
        return country.length() > 0;
    }

    public boolean checkGender() {
        return gender.equals("male") || gender.equals("female");
    }

    public boolean checkPasswordEquals(String confirmedPassword) {
        return password.equals(confirmedPassword.trim());
    }
}
