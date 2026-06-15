package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class ComputeService {

    public long compute() {
        long sum = 0;
        for (int i = 0; i < 50_000_000; i++) {
            sum += Math.sqrt(i);
        }
        return sum;
    }
}
