package com.api.cadastro_dispositivos.controller;

import com.api.cadastro_dispositivos.conections.MQConfig;
import com.api.cadastro_dispositivos.dtos.request.DeviceRequest;
import com.api.cadastro_dispositivos.dtos.request.DeviceListRequest;
import com.api.cadastro_dispositivos.dtos.response.DeviceResponse;
import com.api.cadastro_dispositivos.model.Device;
import com.api.cadastro_dispositivos.services.DeviceService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dispositivos")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private RabbitTemplate template;

    @PostMapping("/registrar")
    public ResponseEntity<DeviceResponse> save(@RequestBody @Valid DeviceRequest deviceRequest) {
        var device = deviceService.save(deviceRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(device);
    }

    @PostMapping("/registrar/async")
    public ResponseEntity<Object> saveList(@RequestBody DeviceListRequest deviceListRequest) {
        template.convertAndSend(MQConfig.EXCHANGE,
                MQConfig.ROUTING_KEY, deviceListRequest);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Device>> getAll() {
        var devices = deviceService.getAll();
        return ResponseEntity.ok(devices);
    }

    @GetMapping("/listar/{deviceId}")
    public ResponseEntity<Device> findById(@PathVariable Long deviceId) {
        var device = deviceService.findById(deviceId);
        return ObjectUtils.isEmpty(device)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(device);
    }

    @PutMapping(value = "/atualizar/{deviceId}")
    public ResponseEntity<Device> update(@PathVariable Long deviceId,
                                         @RequestBody DeviceRequest deviceRequest) {
        return ResponseEntity.ok(deviceService.updateById(deviceId, deviceRequest));
    }

    @DeleteMapping(value = "/deletar/{deviceId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long deviceId) {
        deviceService.deleteById(deviceId);
        return ResponseEntity.noContent().build();
    }

}
