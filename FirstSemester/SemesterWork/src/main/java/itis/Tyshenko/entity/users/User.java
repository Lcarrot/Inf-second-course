package itis.Tyshenko.entity.users;


import lombok.*;

@Getter
public abstract class User {
    protected final String name;
    protected final String surname;
    protected final String login;
    protected final String email;
    protected final String country;
    protected final String hashPassword;
    protected final boolean gender;
}
