package com.example.portal.service;

import com.example.portal.entity.Invoice;
import com.example.portal.entity.Order;
import com.example.portal.entity.User;
import com.example.portal.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public void createInvoice(Order order) {
        Invoice invoice = new Invoice();
        invoice.setOrder(order);
        invoice.setUser(order.getUser());
        invoice.setAmount(order.getTotalAmount());
        invoice.setBillingDate(LocalDateTime.now());
        invoice.setDueDate(LocalDateTime.now().plusDays(30));
        invoiceRepository.save(invoice);
    }

    public List<Invoice> getInvoicesByUser(User user) {
        return invoiceRepository.findByUser(user);
    }
}
