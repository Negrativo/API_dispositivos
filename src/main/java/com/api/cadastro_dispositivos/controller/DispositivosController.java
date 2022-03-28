package com.api.cadastro_dispositivos.controller;

import com.api.cadastro_dispositivos.services.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/dispositivo")
public class DispositivosController {

    @Autowired
    DispositivoService dispositivoService;

}
