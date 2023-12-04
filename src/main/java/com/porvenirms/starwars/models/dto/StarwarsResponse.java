package com.porvenirms.starwars.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StarwarsResponse {
    private int codigo;
    private String mensaje;
    private int status;
}
