package com.api.cadastro_dispositivos.dtos;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class DispositivoDto {

    @NotBlank
    private String name;

    @NotBlank
    private String mac;

    @Size(max = 100)
    private String email;

    private String latitude;

    private String longitude;

}
