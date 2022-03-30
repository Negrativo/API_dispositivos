package com.api.cadastro_dispositivos.consumer;

import com.api.cadastro_dispositivos.dtos.request.DeviceListRequest;
import com.api.cadastro_dispositivos.model.Device;
import com.api.cadastro_dispositivos.repositories.DeviceRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceConsumer {

    @Autowired
    private DeviceRepository deviceRepository;

    @RabbitListener(queues = "dispositivos")
    private void consumer(DeviceListRequest deviceListRequest) {
        deviceListRequest.getDevices()
                .forEach(deviceDto -> {
                    var device = new Device();
                    BeanUtils.copyProperties(deviceDto, device);
                    deviceRepository.save(device);
                });
    }
}
