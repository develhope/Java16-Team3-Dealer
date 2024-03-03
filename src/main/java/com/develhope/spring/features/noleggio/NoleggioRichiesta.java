package com.develhope.spring.features.noleggio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoleggioRichiesta {
    private Long veicoloId;
    private Long acquirenteId;
    private Long venditoreId;
    private boolean pagato;
    private int giorni;
    private Date inizioNoleggio;
    private Date fineNoleggio;
}
