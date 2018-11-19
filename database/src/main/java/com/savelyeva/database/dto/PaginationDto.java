package com.savelyeva.database.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
@Builder
public class PaginationDto {

    private Integer page;

    private Integer limit;
}
