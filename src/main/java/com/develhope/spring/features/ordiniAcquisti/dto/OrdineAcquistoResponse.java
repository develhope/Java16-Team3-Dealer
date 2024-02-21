package com.develhope.spring.features.ordiniAcquisti.dto;

import com.develhope.spring.features.ordiniAcquisti.StatoOrdine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdineAcquistoResponse {
    private Long ordine_id;
    private BigDecimal anticipo;
    private boolean pagato;
    private StatoOrdine stato;
}
