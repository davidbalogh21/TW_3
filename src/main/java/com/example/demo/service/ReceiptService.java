package com.example.demo.service;

import com.example.demo.dao.receipt.ReceiptRepository;
import com.example.demo.model.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptService {
    private final ReceiptRepository receiptRepository;

    @Autowired
    public ReceiptService(@Qualifier("database") ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    public List<Receipt> getReceipts() {
        return receiptRepository.findAll();
    }

    public void save(Receipt receipt) {
        receiptRepository.save(receipt);
    }

    public List<Receipt> getReceiptsByLocation(String location) {
        return receiptRepository.getAllByLocation(location);
    }
}
