package itis.Tyshenko.utility;

import itis.Tyshenko.dto.UserDTO;
import itis.Tyshenko.statuses.SignUpStatus;

import java.util.*;


public class PreparerMessageForSignUp {

    private final Map<SignUpStatus, String> statusDescription;
    List<SignUpStatus> statuses;
    UserDataCheckerRegistration registration;

    public PreparerMessageForSignUp(UserDTO userDTO, String confirmed_password, String agreed) {
        registration = new UserDataCheckerRegistration(userDTO);
        statusDescription = fillMapDescriptions();
        statuses = getStatuses(registration, confirmed_password, agreed);
    }

    public String getMessage() {
        return concatAllErrors(statuses);
    }

    public boolean checkFields() {
        return statuses.get(0).equals(SignUpStatus.OK);
    }

    private Map<SignUpStatus, String> fillMapDescriptions() {
        Map<SignUpStatus, String> statusDescription = new HashMap<>();
        statusDescription.put(SignUpStatus.WRONG_LOGIN, "login is incorrect, please use only latin letters and numbers," +
                " length of login must be more then 3 chars");
        statusDescription.put(SignUpStatus.WRONG_EMAIL, "Email is incorrect");
        statusDescription.put(SignUpStatus.WRONG_PASSWORD, "Password is incorrect, please use latin letters(need no less then 1 uppercase and 1 lowercase)," +
                "number(no less then 1, and special chars(@#%$^), DON'T USE SPACE");
        statusDescription.put(SignUpStatus.PASSWORDS_DO_NOT_MATCH, "password don't match");
        statusDescription.put(SignUpStatus.NO_CHOOSE_COUNTRY, "please, choose your country");
        statusDescription.put(SignUpStatus.NO_CHOOSE_GENDER, "please, choose your gender");
        statusDescription.put(SignUpStatus.NO_AGREED, "if you don't agree with our rules, you can't use our site =(");
        return statusDescription;
    }

    private List<SignUpStatus> getStatuses(UserDataCheckerRegistration checkerRegistration, String confirm_password, String agree) {
        List<SignUpStatus> statuses = new LinkedList<>();
        if (!checkerRegistration.checkLogin()) statuses.add(SignUpStatus.WRONG_LOGIN);
        if (!checkerRegistration.checkEmail()) statuses.add(SignUpStatus.WRONG_EMAIL);
        if (!checkerRegistration.checkCountry()) statuses.add(SignUpStatus.NO_CHOOSE_COUNTRY);
        if (!checkerRegistration.checkGender()) statuses.add(SignUpStatus.NO_CHOOSE_GENDER);
        if (!checkerRegistration.checkPassword()) statuses.add(SignUpStatus.WRONG_PASSWORD);
        if (!checkerRegistration.checkPasswordEquals(confirm_password)) statuses.add(SignUpStatus.PASSWORDS_DO_NOT_MATCH);
        if (agree == null || !(agree.equals("on"))) statuses.add(SignUpStatus.NO_AGREED);
        if (statuses.size() == 0) statuses.add(SignUpStatus.OK);
        return statuses;
    }

    private String concatAllErrors(List<SignUpStatus> statuses) {
        StringBuilder builder = new StringBuilder();
        int i = 1;
        for (SignUpStatus status: statuses) {
            builder.append(i++).append(statusDescription.get(status)).append("\n");
        }
        return builder.toString();
    }
}
