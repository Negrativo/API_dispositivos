package com.api.cadastro_dispositivos.controller;

import com.api.cadastro_dispositivos.dtos.DispositivoDto;
import com.api.cadastro_dispositivos.model.Dispositivo;
import com.api.cadastro_dispositivos.services.DispositivoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dispositivos")
public class DispositivosController {

    @Autowired
    DispositivoService dispositivoService;

    @PostMapping()
    public ResponseEntity<Object> save(@RequestBody @Valid DispositivoDto dispositivoDto) {
        var dispositivo = new Dispositivo();
        BeanUtils.copyProperties(dispositivoDto, dispositivo);
        return dispositivoService.save(dispositivo);
    }

    @GetMapping
    public List<Dispositivo> getAll() {
        return dispositivoService.getAll();
    }

    @GetMapping("/{id}")
    public Dispositivo getById(@PathVariable("deviceId") Long deviceId){
        return dispositivoService.findById(deviceId);
    }

}
