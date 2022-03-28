package com.api.cadastro_dispositivos.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_DISPOSITIVOS")
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "mac", nullable = false)
    private String mac;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Dispositivo that = (Dispositivo) o;
        return deviceId != null && Objects.equals(deviceId, that.deviceId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
