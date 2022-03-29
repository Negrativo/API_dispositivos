package com.api.cadastro_dispositivos.consumer;

import com.api.cadastro_dispositivos.dtos.DispositivoDto;
import com.api.cadastro_dispositivos.dtos.DispositivoListDto;
import com.api.cadastro_dispositivos.model.Dispositivo;
import com.api.cadastro_dispositivos.repositories.DispositivoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DispositivoConsumer {

    @Autowired
    DispositivoRepository dispositivoRepository;

    @RabbitListener(queues = "dispositivos")
    private void consumer(DispositivoListDto dispositivoListDto) {
        List<DispositivoDto> dispositivos = dispositivoListDto.getDispositivos();
        for (DispositivoDto dispositivoDto : dispositivos) {
            var dispositivo = new Dispositivo();
            BeanUtils.copyProperties(dispositivoDto, dispositivo);
            dispositivoRepository.save(dispositivo);
        }
    }
}
