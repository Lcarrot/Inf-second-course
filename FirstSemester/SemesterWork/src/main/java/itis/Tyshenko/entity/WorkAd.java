package itis.Tyshenko.entity;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class WorkAd extends Ad {

    private Long price;
}
