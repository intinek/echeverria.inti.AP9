package com.mindhub.homebanking1.dtos;

import com.mindhub.homebanking1.models.Transaction;
import com.mindhub.homebanking1.models.TransactionType;

import java.time.LocalDate;

public class TransactionDTO {
    private long id;
    private TransactionType type;
    private Double amount;
    private String description;
    private LocalDate date = LocalDate.now();

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.type = transaction.getType();
        this.amount = transaction.getAmount();
        this.description = transaction.getDescription();
        this.date = transaction.getDate();
    }

    public TransactionType getType() {
        return type;
    }

    public Double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }
}
