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

@RestController
@RequestMapping("/dispositivos")
public class DispositivosController {

    @Autowired
    DispositivoService dispositivoService;

    @PostMapping("/registrar")
    public ResponseEntity<Object> save(@RequestBody @Valid DispositivoDto dispositivoDto) {
        var dispositivo = new Dispositivo();
        BeanUtils.copyProperties(dispositivoDto, dispositivo);
        return dispositivoService.save(dispositivo);
    }

    @GetMapping("/listar")
    public List<Dispositivo> getAll() {
        return dispositivoService.getAll();
    }

    @GetMapping("/listar/{deviceId}")
    public ResponseEntity<Dispositivo> findById(@PathVariable Long deviceId){
        return dispositivoService.findById(deviceId);
    }

    @PutMapping(value="/atualizar/{deviceId}")
    public ResponseEntity<Dispositivo> update(@PathVariable long deviceId,
                                          @RequestBody DispositivoDto dispositivoDto){
        return dispositivoService.updateById(deviceId, dispositivoDto);

    }

    @DeleteMapping(value="/deletar/{deviceId}")
    public ResponseEntity<Object> deleteById(@PathVariable long deviceId){
        return dispositivoService.deleteById(deviceId);

    }

}
