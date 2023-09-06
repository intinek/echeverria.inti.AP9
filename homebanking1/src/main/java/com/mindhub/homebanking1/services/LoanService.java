package com.mindhub.homebanking1.services;

import com.mindhub.homebanking1.dtos.LoanDTO;
import com.mindhub.homebanking1.models.Loan;

import java.util.List;

public interface LoanService {
    List<LoanDTO> getLoans();

    Loan findLoanById(Long loanId);

}
