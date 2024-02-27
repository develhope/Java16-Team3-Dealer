package com.develhope.spring.features.shared;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Error {
    private int code;
    private String message;
}
