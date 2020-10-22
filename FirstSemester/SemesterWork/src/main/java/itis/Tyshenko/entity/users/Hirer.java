package itis.Tyshenko.entity.users;

import lombok.*;

@Builder
public class Hirer extends User {
    private final String name;
    private final String surname;
    private final String login;
    private final String email;
    private final String country;
    private final int hashPassword;
    private final boolean gender;
    private final String organization;
}
