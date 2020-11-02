package itis.Tyshenko.entity;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class Ad {

    protected String id;
    protected String header;
    protected String description;
    private String contact;
}
