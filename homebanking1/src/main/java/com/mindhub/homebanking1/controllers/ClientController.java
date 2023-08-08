package com.mindhub.homebanking1.controllers;

import com.mindhub.homebanking1.dtos.ClientDTO;
import com.mindhub.homebanking1.models.Client;
import com.mindhub.homebanking1.repositories.ClientRepository;
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
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;
    @GetMapping("/clients")
    public List<ClientDTO> getClients(){
        List<Client> allClients =  clientRepository.findAll();

        List<ClientDTO> converterList = allClients
                .stream()
                .map(currentClient -> new ClientDTO(currentClient))
                .collect(Collectors.toList());
        return converterList;
    }

    @GetMapping("/clients/{id}")
    public ClientDTO getClientById(@PathVariable Long id){
        Optional<Client> client = clientRepository.findById(id);
        return new ClientDTO(client.get());
    }
}

