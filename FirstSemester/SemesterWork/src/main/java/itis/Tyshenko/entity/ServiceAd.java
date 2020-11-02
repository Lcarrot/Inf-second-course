package itis.Tyshenko.entity;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ServiceAd extends Ad {

    private String status;
}
