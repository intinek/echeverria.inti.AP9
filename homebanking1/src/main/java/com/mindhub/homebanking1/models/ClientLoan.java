package com.mindhub.homebanking1.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class ClientLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private double amount;
    @ElementCollection
    private List<Integer> payments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="clientId")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="loanId")
    private Loan loan;

    public ClientLoan() {
    }

    public ClientLoan(double amount, List<Integer> payments, Client client, Loan loan) {
        this.amount = amount;
        this.payments = payments;
        this.client = client;
        this.loan = loan;
    }

    public long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }
}
