package com.api.cadastro_dispositivos.dtos.response;

import com.api.cadastro_dispositivos.model.Device;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceResponse {

    private Long id;

    private String mac;

    public static DeviceResponse of(Device device) {
        return DeviceResponse.builder()
                .id(device.getId())
                .mac(device.getMac())
                .build();
    }
}
