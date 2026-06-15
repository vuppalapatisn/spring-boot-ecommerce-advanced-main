
package com.example.payment;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitListeners {
    @RabbitListener(queues = "order.created")
    public void onOrderCreated(String payload){
        System.out.println("[payment-service] received order event: " + payload);
    }
}
