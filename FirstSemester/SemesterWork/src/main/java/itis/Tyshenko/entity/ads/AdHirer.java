package itis.Tyshenko.entity.ads;

import lombok.Builder;

@Builder
public class AdHirer extends Ad {
    private String name;
    private String description;
}
