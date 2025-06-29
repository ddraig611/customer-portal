package com.example.portal.client;

import com.example.portal.entity.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ProductionServiceClient {

    private final Random random = new Random();

    @Value("${production.mock.force-result:RANDOM}")
    private String forceResult;

    public boolean pushOrder(Order order) {
        System.out.println("[MOCK] Calling internal Production system for order: " + order.getId());

        if ("SUCCESS".equalsIgnoreCase(forceResult)) {
            System.out.println("[MOCK] Forced Production result: SUCCESS");
            return true;
        } else if ("FAILURE".equalsIgnoreCase(forceResult)) {
            System.out.println("[MOCK] Forced Production result: FAILURE");
            return false;
        }

        boolean result = random.nextBoolean();
        System.out.println("[MOCK] Random Production result: " + (result ? "SUCCESS" : "FAILURE"));
        return result;
    }
}
