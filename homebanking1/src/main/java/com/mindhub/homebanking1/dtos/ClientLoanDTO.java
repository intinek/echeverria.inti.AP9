package com.mindhub.homebanking1.dtos;

import com.mindhub.homebanking1.models.ClientLoan;

import java.util.List;
import java.util.stream.Collectors;

public class ClientLoanDTO {
    private long id;

    private long loanId;
    private double amount;
    private int payments;
    private String name;


    public ClientLoanDTO(ClientLoan clientLoan) {
        this.id = clientLoan.getId();
        this.name = clientLoan.getLoan().getName();
        this.loanId = clientLoan.getLoan().getId();
        this.amount = clientLoan.getAmount();
        this.payments = clientLoan.getPayments();


    }

    public long getId() {
        return id;
    }

    public long getLoanId() {
        return loanId;
    }

    public double getAmount() {
        return amount;
    }

    public int getPayments() {
        return payments;
    }

    public String getName() {
        return name;
    }

}
