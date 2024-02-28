package com.develhope.spring.features.signUpSignIn;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Data
@Getter
@Setter
@Component
public class IDLogin {

    private Long id;
    private String tipoUtente;
}
