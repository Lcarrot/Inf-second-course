package ru.itis.tyshenko.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

@Builder
@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements ru.itis.tyshenko.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String email;
    private String country;
    private String hashPassword;
    private String gender;
    private String confirmCode;
    private boolean confirmed = false;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "owner")
    private List<Ad> ads;

    @OneToMany(mappedBy = "owner")
    private List<Resume> resumes;

    public enum  Role {
        ADMIN, USER
    }

    public boolean isAdmin() {
        return this.role.equals(Role.ADMIN);
    }
}
