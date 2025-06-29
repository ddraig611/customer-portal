package com.example.portal.controller;

import com.example.portal.entity.User;
import com.example.portal.service.InvoiceService;
import com.example.portal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final UserService userService;

    @GetMapping
    public String listInvoices(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName()).orElseThrow();
        model.addAttribute("invoices", invoiceService.getInvoicesByUser(user));
        return "invoices";
    }
}
