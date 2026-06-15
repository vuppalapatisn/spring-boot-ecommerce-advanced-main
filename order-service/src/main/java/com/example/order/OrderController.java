
package com.example.order;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/orders")
public class OrderController {
    record CreateOrderReq(String userId, double amount){}
    record PaymentRes(boolean success, String txnId){}
    record OrderRes(String status, String paymentTxnId){}

    private final WebClient.Builder webClientBuilder;
    private final RabbitTemplate rabbitTemplate;

    public OrderController(WebClient.Builder webClientBuilder, RabbitTemplate rabbitTemplate){
        this.webClientBuilder = webClientBuilder; this.rabbitTemplate = rabbitTemplate;
    }

    @Bean
    public Queue orderCreatedQueue(){ return new Queue("order.created", true); }

    @PostMapping
    public ResponseEntity<OrderRes> create(@RequestBody CreateOrderReq req){
        PaymentRes pr = webClientBuilder.build()
            .post().uri("http://payment-service/payments/charge")
            .bodyValue(req)
            .retrieve().bodyToMono(PaymentRes.class).block();
        String status = (pr != null && pr.success()) ? "PAID" : "FAILED";
        String txn = (pr != null) ? pr.txnId() : null;
        // publish event
        rabbitTemplate.convertAndSend("order.created", "user="+req.userId()+", amount="+req.amount()+", txn="+txn);
        return ResponseEntity.ok(new OrderRes(status, txn));
    }
}
