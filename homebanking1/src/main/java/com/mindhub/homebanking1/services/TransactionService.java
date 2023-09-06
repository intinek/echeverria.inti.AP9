package com.mindhub.homebanking1.services;

import com.mindhub.homebanking1.models.Transaction;

public interface TransactionService {
    void transactionSave(Transaction transaction);
}
