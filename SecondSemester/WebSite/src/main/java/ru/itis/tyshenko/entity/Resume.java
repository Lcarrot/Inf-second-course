package ru.itis.tyshenko.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Resume implements ru.itis.tyshenko.entity.Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String header;
    private String description;
    private String contact;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}
