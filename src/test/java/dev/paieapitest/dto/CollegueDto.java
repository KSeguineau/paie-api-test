package dev.paieapitest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollegueDto {

    private String matricule;
    private String nom;
    private String prenom;
    private String email;
    private LocalDate ddn;
    private String photoUrl;


}
