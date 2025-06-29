package com.example.portal.client;

import com.example.portal.entity.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PaymentServiceClient {

    private final Random random = new Random();

    /**
     * Optional: allow forcing payment result for testing.
     * Can be set in application.properties as:
     * payment.mock.force-result=SUCCESS or FAILURE or RANDOM
     */
    @Value("${payment.mock.force-result:RANDOM}")
    private String forceResult;

    public boolean charge(Order order) {
        System.out.println("[MOCK] Charging payment for order: " + order.getId());

        // Simulate forced outcome for testing
        if ("SUCCESS".equalsIgnoreCase(forceResult)) {
            System.out.println("[MOCK] Forced payment result: SUCCESS");
            return true;
        } else if ("FAILURE".equalsIgnoreCase(forceResult)) {
            System.out.println("[MOCK] Forced payment result: FAILURE");
            return false;
        }

        // Default: random success or failure
        boolean result = random.nextBoolean();
        System.out.println("[MOCK] Random payment result: " + (result ? "SUCCESS" : "FAILURE"));
        return result;
    }
}
