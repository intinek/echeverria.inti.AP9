package com.mindhub.homebanking1.controllers;

import com.mindhub.homebanking1.dtos.AccountDTO;
import com.mindhub.homebanking1.models.Account;
import com.mindhub.homebanking1.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @GetMapping("/accounts")
    public List<AccountDTO> getAccounts() {
        List<Account> allAccounts = accountRepository.findAll();

        List<AccountDTO> converterList = allAccounts
                .stream()
                .map(currentAccount -> new AccountDTO(currentAccount))
                .collect(Collectors.toList());

        return converterList;

    }

    @GetMapping("/accounts/{id}")
    public AccountDTO getAccountById(@PathVariable Long id){
        Optional<Account> account = accountRepository.findById(id);
        return new AccountDTO(account.get());
    }

}
