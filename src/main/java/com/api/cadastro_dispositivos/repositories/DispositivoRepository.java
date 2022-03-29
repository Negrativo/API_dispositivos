package com.api.cadastro_dispositivos.repositories;

import com.api.cadastro_dispositivos.model.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {
}
