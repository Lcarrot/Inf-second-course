package ru.itis.tyshenko.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ad implements ru.itis.tyshenko.entity.Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String header;
    private String description;
    private String contact;
    private Long price;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}
