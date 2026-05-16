package com.project001.Regency.controller;

import com.project001.Regency.model.Billing;
import com.project001.Regency.service.BillingServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/billings")
public class BillingController {

    private final BillingServiceImpl billingService;

    public BillingController(BillingServiceImpl billingService) {
        this.billingService = billingService;
    }

    // CREATE
    @PostMapping
    public Billing addBilling(@RequestBody Billing billing) {
        return billingService.addBilling(billing);
    }

    // READ
    @GetMapping
    public List<Billing> getAllBillings() {
        return billingService.getAllBillings();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteBilling(@PathVariable Long id) {
        billingService.deleteBilling(id);
    }
}

