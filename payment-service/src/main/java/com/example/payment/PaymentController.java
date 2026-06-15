
package com.example.payment;

import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    record ChargeReq(String userId, double amount){}
    record ChargeRes(boolean success, String txnId){}
    @PostMapping("/charge")
    public ChargeRes charge(@RequestBody ChargeReq req){ return new ChargeRes(true, UUID.randomUUID().toString()); }
}
