package com.api.cadastro_dispositivos.services;

import com.api.cadastro_dispositivos.dtos.DispositivoDto;
import com.api.cadastro_dispositivos.model.Dispositivo;
import com.api.cadastro_dispositivos.repositories.DispositivoRepository;
import com.api.cadastro_dispositivos.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DispositivoService {

    @Autowired
    DispositivoRepository dispositivoRepository;

    @Transactional
    public ResponseEntity<Object> save(Dispositivo dispositivo) {
        try {
            Dispositivo dispositivoSaved = dispositivoRepository.save(dispositivo);
            return ResponseHandler.generateResponse(HttpStatus.CREATED, dispositivoSaved);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Transactional
    public List<Dispositivo> getAll() {
        return dispositivoRepository.findAll();
    }

    @Transactional
    public ResponseEntity<Dispositivo> findById(Long deviceId) {
        return dispositivoRepository.findById(deviceId)
                .map(device -> ResponseEntity.ok().body(device))
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    public ResponseEntity<Dispositivo> updateById(Long deviceId, DispositivoDto dispositivoDto) {
        return dispositivoRepository.findById(deviceId)
                .map(dispositivo -> {
                    dispositivo.setName(dispositivoDto.getName());
                    dispositivo.setEmail(dispositivoDto.getEmail());
                    dispositivo.setMac(dispositivoDto.getMac());
                    dispositivo.setLatitude(dispositivoDto.getLatitude());
                    dispositivo.setLatitude(dispositivoDto.getLatitude());
                    Dispositivo updated = dispositivoRepository.save(dispositivo);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    public ResponseEntity<Object> deleteById(Long deviceId) {
        return dispositivoRepository.findById(deviceId)
                .map(device -> {
                    dispositivoRepository.deleteById(device.getDeviceId());
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
