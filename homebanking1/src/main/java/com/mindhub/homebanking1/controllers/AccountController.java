package com.mindhub.homebanking1.controllers;

import com.mindhub.homebanking1.dtos.AccountDTO;
import com.mindhub.homebanking1.models.Account;
import com.mindhub.homebanking1.models.Client;
import com.mindhub.homebanking1.repositories.AccountRepository;
import com.mindhub.homebanking1.repositories.ClientRepository;
import com.mindhub.homebanking1.services.AccountService;
import com.mindhub.homebanking1.services.ClientService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;


import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientService clientService;

    @GetMapping("/accounts")
    public List<AccountDTO> getAccounts() {
        return accountService.getAccounts();

    }

    @RequestMapping("/accounts/{id}")
    public ResponseEntity<Object>getAccount(@PathVariable Long id, Authentication authentication) {
        Client client = clientService.findByEmail(authentication.getName());
        Account account = accountService.findByID(id);

        if (account == null){
            return new ResponseEntity<>("Account not found", HttpStatus.BAD_GATEWAY);
        }
        if (account.getClient().equals(client)) {
            AccountDTO accountDTO = new AccountDTO(account);
            return new ResponseEntity<>(accountDTO, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("This Account is not yours", HttpStatus.I_AM_A_TEAPOT);
        }

    }

    @GetMapping("/clients/current/accounts")
    public List<AccountDTO> getCurrentAccounts(Authentication authentication){
        Client client = clientService.findByEmail(authentication.getName());
        return client.getAccounts().stream().map(AccountDTO::new).collect(toList());
    }


    @PostMapping("/clients/current/accounts")
    public ResponseEntity<Object> createAccounts(Authentication authentication){
        Client clientAuth = clientService.findByEmail(authentication.getName());
        Set<Account> allAccounts =new HashSet<>();
        allAccounts = clientAuth.getAccounts();

        if (allAccounts.size() >= 3)
            return new ResponseEntity<>("you have three accounts, you can't create more", HttpStatus.FORBIDDEN);
        else {

            String numberAccount;
            Random random= new Random();
            numberAccount = "VIN" + random.nextInt(90000000);
            Account account = new Account(numberAccount, LocalDate.now(), 0);
            clientAuth.addAccount(account);
            accountService.accountSave(account);
            clientService.clientSave(clientAuth);
            return new ResponseEntity<>("New account created", HttpStatus.CREATED );
        }

    }



}
