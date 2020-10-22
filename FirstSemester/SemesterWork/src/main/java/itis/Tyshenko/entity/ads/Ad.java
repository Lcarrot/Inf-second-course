package itis.Tyshenko.entity.ads;

import lombok.*;

@Builder
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class Ad {
    private String name;
    private String description;
}
