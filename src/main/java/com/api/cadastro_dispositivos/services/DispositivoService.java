package com.api.cadastro_dispositivos.services;

import com.api.cadastro_dispositivos.repositories.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DispositivoService {

    @Autowired
    DispositivoRepository dispositivoRepository;

}
