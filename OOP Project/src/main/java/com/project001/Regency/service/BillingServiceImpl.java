package com.project001.Regency.service;

import com.project001.Regency.model.Billing;
import com.project001.Regency.repository.BillingRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingServiceImpl {

    private final BillingRepo billingRepo;

    public BillingServiceImpl(BillingRepo billingRepo) {
        this.billingRepo = billingRepo;
    }

    // ADD BILL
    public Billing addBilling(Billing billing) {
        return billingRepo.save(billing);
    }

    // GET ALL
    public List<Billing> getAllBillings() {
        return billingRepo.findAll();
    }

    // DELETE
    public void deleteBilling(Long id) {
        billingRepo.deleteById(id);
    }
}