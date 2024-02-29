package com.develhope.spring.features.noleggio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
