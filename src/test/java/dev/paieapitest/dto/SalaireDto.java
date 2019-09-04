package dev.paieapitest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaireDto {

    private BigDecimal salaireBrut;
    private BigDecimal salaireNetImposable;
    private BigDecimal salaireNetAPayer;
}
