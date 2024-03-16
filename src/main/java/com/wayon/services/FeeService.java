package com.wayon.services;

import com.wayon.domain.fee.Fee;
import com.wayon.repositories.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeeService {
    @Autowired
    private FeeRepository feeRepository;

    public Fee getFeeFromDiffDay(Integer diff) {
        List<Fee> fees = this.getAllFees();

        return fees.stream()
                .filter(fee -> diff >= fee.fromDate && diff <= fee.toDate)
                .collect(Collectors.toList())
                .get(0);
    }

    public List<Fee> getAllFees() {
        return this.feeRepository.findAll();
    }
}
