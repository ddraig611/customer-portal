package com.example.portal.service;

import com.example.portal.client.EmailServiceClient;
import com.example.portal.client.PaymentServiceClient;
import com.example.portal.client.ProductionServiceClient;
import com.example.portal.entity.Order;
import com.example.portal.entity.Payment;
import com.example.portal.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentServiceClient paymentServiceClient;
    private final ProductionServiceClient productionServiceClient;
    private final EmailServiceClient emailServiceClient;
    private final InvoiceService invoiceService;
    private final OrderService orderService;

    public boolean checkout(Order order) {
        boolean paymentResult = paymentServiceClient.charge(order);
        if (!paymentResult) {
            return false;
        }

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(order.getTotalAmount());
        payment.setStatus("SUCCESS");
        payment.setPaidAt(LocalDateTime.now());
        paymentRepository.save(payment);

        invoiceService.createInvoice(order);

        boolean productionResult = productionServiceClient.pushOrder(order);
        if (!productionResult) {
            order.setStatus("PENDING_PRODUCTION");
        } else {
            order.setStatus("CHECKED_OUT");
        }
        orderService.updateOrder(order);

        emailServiceClient.sendSuccessEmail(order.getUser().getEmail(), order.getName());
        return true;
    }
}
