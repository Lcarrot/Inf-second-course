package itis.Tyshenko;

public class User {
    private final String name;
    private final String email;
    private final String country;
    private final int hashPassword;
    private final boolean gender;

    public User(String name, String email, String country, int hashPassword, boolean gender) {
        this.name = name;
        this.email = email;
        this.country = country;
        this.hashPassword = hashPassword;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public int getHashPassword() {
        return hashPassword;
    }

    public boolean isGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", hashPassword=" + hashPassword +
                ", gender=" + gender +
                '}';
    }
}
