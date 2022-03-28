package com.api.cadastro_dispositivos.services;

import com.api.cadastro_dispositivos.model.Dispositivo;
import com.api.cadastro_dispositivos.repositories.DispositivoRepository;
import com.api.cadastro_dispositivos.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DispositivoService {

    @Autowired
    DispositivoRepository dispositivoRepository;

    @Transactional
    public ResponseEntity<Object> save(Dispositivo dispositivo) {
        try {
            Dispositivo dispositivoSave = dispositivoRepository.save(dispositivo);
            return ResponseHandler.generateResponse(HttpStatus.CREATED, dispositivoSave);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, e.getMessage());
        }
    }
}
