package com.api.cadastro_dispositivos.controller;

import com.api.cadastro_dispositivos.conections.MQConfig;
import com.api.cadastro_dispositivos.dtos.DispositivoDto;
import com.api.cadastro_dispositivos.dtos.DispositivoListDto;
import com.api.cadastro_dispositivos.model.Dispositivo;
import com.api.cadastro_dispositivos.services.DispositivoService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dispositivos")
public class DispositivosController {

    @Autowired
    DispositivoService dispositivoService;
    @Autowired
    private RabbitTemplate template;

    @PostMapping("/registrar")
    public ResponseEntity<Object> save(@RequestBody @Valid DispositivoDto dispositivoDto) {
        var dispositivo = new Dispositivo();
        BeanUtils.copyProperties(dispositivoDto, dispositivo);
        return dispositivoService.save(dispositivo);
    }

    @PostMapping("/registrar/lista")
    public ResponseEntity<Object> saveList(@RequestBody DispositivoListDto dispositivoListDto) {
        template.convertAndSend(MQConfig.EXCHANGE,
                MQConfig.ROUTING_KEY, dispositivoListDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
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
