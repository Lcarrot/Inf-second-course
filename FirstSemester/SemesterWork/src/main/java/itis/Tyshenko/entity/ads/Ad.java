package itis.Tyshenko.entity.ads;

import lombok.Getter;

@Getter
public abstract class Ad {
    private String id;
    private String name;
    private String description;
    private String service;
    private Integer lowPrice;
    private Integer highPrice;
}
