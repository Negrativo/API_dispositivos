package com.api.cadastro_dispositivos.repositories;

import com.api.cadastro_dispositivos.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
