package dev.paieapitest.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemunerationEmployeDto {

    private String matricule;
    private ZonedDateTime dateCreation;
    private String gradeCode;


}
