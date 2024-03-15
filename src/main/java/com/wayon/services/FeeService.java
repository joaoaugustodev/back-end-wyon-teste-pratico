package com.wayon.services;

import com.wayon.domain.fee.Fee;
import com.wayon.repositories.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeService {
    @Autowired
    private FeeRepository feeRepository;

    public Fee getFeeFromDiffDay(Integer diff) {
        Fee result = null;
        List<Fee> fees = this.getAllFees();

        for (Fee fee : fees) {
            if (diff >= fee.fromDate && diff <= fee.toDate) {
                result = fee;
            }
        }

        return result;
    }

    public List<Fee> getAllFees() {
        return this.feeRepository.findAll();
    }
}
