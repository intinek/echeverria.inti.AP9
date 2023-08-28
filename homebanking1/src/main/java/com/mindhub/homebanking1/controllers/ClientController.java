package com.mindhub.homebanking1.controllers;

import com.mindhub.homebanking1.dtos.ClientDTO;
import com.mindhub.homebanking1.models.Account;
import com.mindhub.homebanking1.models.Client;
import com.mindhub.homebanking1.repositories.AccountRepository;
import com.mindhub.homebanking1.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;
    @GetMapping("/clients")
    public List<ClientDTO> getClients(){
        return clientRepository.findAll()

                .stream()
                .map(currentClient -> new ClientDTO(currentClient))
                .collect(toList());

    }

    @GetMapping("/clients/{id}")
    public ClientDTO getClientById(@PathVariable Long id){
        return new ClientDTO(clientRepository.findById(id).get());
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;
    @PostMapping("/clients")
    public ResponseEntity<Object> register(
            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password) {
        if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || password.isBlank()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (clientRepository.findByEmail(email) !=  null) {
            return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);
        }else{
            Client client = new Client(firstName, lastName, email, passwordEncoder.encode(password));

            String numAccount;

            do {
                Random random = new Random();
                numAccount = "VIN-" + random.nextInt(90000000);
            } while (accountRepository.findByNumber(numAccount) != null);

            Account account = new Account(numAccount, LocalDate.now(), 0.0);
            client.addAccount(account);
            clientRepository.save(client);
            accountRepository.save(account);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/clients/current")
    public ClientDTO getClientCurrentAll(Authentication authentication){
        return  new ClientDTO (clientRepository.findByEmail(authentication.getName()));


    }

}

