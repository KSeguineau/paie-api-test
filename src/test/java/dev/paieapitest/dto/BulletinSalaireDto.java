package dev.paieapitest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BulletinSalaireDto {

    private ZonedDateTime dateCreation;
    private Periode periode;
    private String matricule;
    private String code;

}
