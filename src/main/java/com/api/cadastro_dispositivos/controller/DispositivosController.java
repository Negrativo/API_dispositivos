package com.api.cadastro_dispositivos.controller;

import com.api.cadastro_dispositivos.dtos.DispositivoDto;
import com.api.cadastro_dispositivos.model.Dispositivo;
import com.api.cadastro_dispositivos.services.DispositivoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/dispositivo")
public class DispositivosController {

    @Autowired
    DispositivoService dispositivoService;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid DispositivoDto dispositivoDto) {
        var dispositivo = new Dispositivo();
        BeanUtils.copyProperties(dispositivoDto, dispositivo);
        return dispositivoService.save(dispositivo);
    }

}
