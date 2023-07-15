package com.utm.jpacrud.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenderDTO {
    private String department;
    private Long femaleCount;
    private Long maleCount;


}
