package com.api.cadastro_dispositivos.dtos.request;

import lombok.Data;

import java.util.List;

@Data
public class DeviceListRequest {

    List<DeviceRequest> devices;

}
