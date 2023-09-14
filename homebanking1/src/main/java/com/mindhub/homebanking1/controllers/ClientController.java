package com.mindhub.homebanking1.controllers;

import com.mindhub.homebanking1.dtos.ClientDTO;
import com.mindhub.homebanking1.models.Account;
import com.mindhub.homebanking1.models.Client;
import com.mindhub.homebanking1.services.AccountService;
import com.mindhub.homebanking1.services.ClientService;
import com.mindhub.homebanking1.utils.CardUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ClientController {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;


    @GetMapping("/clients")
    public List<ClientDTO> getClients() {

        return clientService.getClients();
    }

    @GetMapping("/clients/{id}")
    public ClientDTO getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }


    @GetMapping("/clients/current")
    public ClientDTO getClientCurrentAll(Authentication authentication){
        return clientService.getClientCurrentAll(authentication.getName());


    }

    @PostMapping("/clients")
    public ResponseEntity<Object> register(@RequestParam String firstName,
                                           @RequestParam String lastName,
                                           @RequestParam String email,
                                           @RequestParam String password) {


        if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || password.isBlank()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (clientService.findByEmail(email) !=  null) {
            return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);
        }else{
            Client client = new Client(firstName, lastName, email, passwordEncoder.encode(password));

            String numAccount;


            do {
                numAccount = CardUtils.generateRandomVIN();
            } while (accountService.findByNumber(numAccount) != null);

            Account account = new Account(numAccount, LocalDate.now(), 0.0);
            client.addAccount(account);
            clientService.clientAdd(client);
            accountService.accountSave(account);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }




}

