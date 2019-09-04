package dev.paieapitest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Periode {

    private Integer id;
    private LocalDate dateDebut;
    private LocalDate dateFin;


}
