package com.example.portal.client;

import org.springframework.stereotype.Component;

@Component
public class EmailServiceClient {

    /**
     * Mock sending success email to client after payment.
     *
     * @param toEmail the recipient's email
     * @param orderName the name of the order
     */
    public void sendSuccessEmail(String toEmail, String orderName) {
        System.out.println("[MOCK EMAIL] Sending checkout success email to: " + toEmail);
        System.out.println("[MOCK EMAIL] Subject: Your Order '" + orderName + "' Has Been Checked Out Successfully!");
        System.out.println("[MOCK EMAIL] Body: Hello, your payment was successful and your order '" 
                           + orderName + "' is now being processed.");
        System.out.println("[MOCK EMAIL] -- EMAIL SENT --");
    }
}
