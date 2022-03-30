package com.api.cadastro_dispositivos.services;

import com.api.cadastro_dispositivos.dtos.request.DeviceRequest;
import com.api.cadastro_dispositivos.exceptions.NotFoundExecption;
import com.api.cadastro_dispositivos.model.Device;
import com.api.cadastro_dispositivos.repositories.DeviceRepository;
import com.api.cadastro_dispositivos.dtos.response.DeviceResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Transactional
    public DeviceResponse save(DeviceRequest deviceRequest) {
        var device = new Device();
        BeanUtils.copyProperties(deviceRequest, device);
        Device deviceSaved = deviceRepository.save(device);
        return DeviceResponse.of(deviceSaved);
    }

    public List<Device> getAll() {
        return deviceRepository.findAll();
    }

    public Device findById(Long deviceId) {
        return deviceRepository.findById(deviceId)
                .orElse(null);
    }

    @Transactional
    public Device updateById(Long deviceId, DeviceRequest deviceRequest) {
        return deviceRepository.findById(deviceId)
                .map(dispositivo -> {
                    dispositivo.setName(deviceRequest.getName());
                    dispositivo.setEmail(deviceRequest.getEmail());
                    dispositivo.setMac(deviceRequest.getMac());
                    dispositivo.setLatitude(deviceRequest.getLatitude());
                    dispositivo.setLatitude(deviceRequest.getLatitude());
                    return deviceRepository.save(dispositivo);
                }).orElseThrow(() -> new NotFoundExecption("Dispositivo não encontrado para atualização."));
    }

    @Transactional
    public void deleteById(Long deviceId) {
        deviceRepository.findById(deviceId)
                .ifPresentOrElse(disp -> deviceRepository.deleteById(disp.getId()), () -> {
                    throw new NotFoundExecption("Dispositivo não encontrado para remoção.");
                });
    }
}
