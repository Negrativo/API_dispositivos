package com.api.cadastro_dispositivos.response;

import com.api.cadastro_dispositivos.model.Dispositivo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public final class ResponseHandler {

    private ResponseHandler() {}

    public static ResponseEntity<Object> generateResponse(HttpStatus status, Dispositivo responseObj) {
        Map<String, Object> responseBody = new HashMap<>();
        Map<String, Object> objectBody = new HashMap<>();

        objectBody.put("deviceId", responseObj.getId());
        objectBody.put("mac", responseObj.getMac());
        responseBody.put("status", status.value());
        responseBody.put("data", objectBody);

        return new ResponseEntity<>(responseBody,status);
    }

    public static ResponseEntity<Object> generateResponse(HttpStatus status, Object responseObj) {
        Map<String, Object> responseBody = new HashMap<>();

        responseBody.put("status", status.value());
        responseBody.put("data", responseObj);

        return new ResponseEntity<>(responseBody,status);
    }
}
