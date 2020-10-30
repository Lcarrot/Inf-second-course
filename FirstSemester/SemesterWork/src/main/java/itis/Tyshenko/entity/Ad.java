package itis.Tyshenko.entity;

import lombok.Getter;

@Getter
public abstract class Ad {
    protected String id;
    protected String name;
    protected String description;
    protected String theme;
}
