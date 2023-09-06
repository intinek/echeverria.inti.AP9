package com.mindhub.homebanking1.services.implement;

import com.mindhub.homebanking1.dtos.LoanDTO;
import com.mindhub.homebanking1.models.Loan;
import com.mindhub.homebanking1.repositories.LoanRepository;
import com.mindhub.homebanking1.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImplement implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public List<LoanDTO> getLoans() {
        return loanRepository.findAll()
                .stream()
                .map(currentLoan -> new LoanDTO(currentLoan))
                .collect(Collectors.toList());
    }

    @Override
    public Loan findLoanById(Long loanId) {
        return loanRepository.findLoanById(loanId);
    }
}
