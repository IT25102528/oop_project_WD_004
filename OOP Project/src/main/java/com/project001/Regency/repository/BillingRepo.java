package com.project001.Regency.repository;

import com.project001.Regency.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepo extends JpaRepository<Billing, Long> {
}

