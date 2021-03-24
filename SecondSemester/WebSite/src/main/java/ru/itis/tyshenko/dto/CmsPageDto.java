package ru.itis.tyshenko.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CmsPageDto implements Dto {
    private Long id;
    private String name;
    private String content;
}
