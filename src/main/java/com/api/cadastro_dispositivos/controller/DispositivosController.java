package com.api.cadastro_dispositivos.controller;

import com.api.cadastro_dispositivos.dtos.DispositivoDto;
import com.api.cadastro_dispositivos.model.Dispositivo;
import com.api.cadastro_dispositivos.services.DispositivoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/dispositivo")
public class DispositivosController {

    @Autowired
    DispositivoService dispositivoService;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid DispositivoDto dispositivoDto) {
        var dispositivoModel = new Dispositivo();
        BeanUtils.copyProperties(dispositivoDto, dispositivoModel);
        return dispositivoService.save(dispositivoModel);
    }

}
