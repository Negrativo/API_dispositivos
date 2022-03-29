package com.api.cadastro_dispositivos.conections;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConection {

    private final AmqpAdmin amqpAdmin;

    public RabbitMQConection(AmqpAdmin amqpAdmin) {
//        CachingConnectionFactory connectionFactory =
//                new CachingConnectionFactory("localhost:15672");
//
//        connectionFactory.setUsername("user");
//        connectionFactory.setPassword("admin");
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila() {
        return new Queue("DISPOSITIVOS", true, false, false);
    }

    private DirectExchange trocaDireta() {
        return new DirectExchange("amq.direct");
    }

    private Binding relacionamento(Queue fila, DirectExchange troca){
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }

    @PostConstruct
    private void adiciona(){
        Queue fila = this.fila();
        DirectExchange troca = this.trocaDireta();
        Binding relaciona = this.relacionamento(fila, troca);

        this.amqpAdmin.declareQueue(fila);
        this.amqpAdmin.declareExchange(troca);
        this.amqpAdmin.declareBinding(relaciona);
    }
}
