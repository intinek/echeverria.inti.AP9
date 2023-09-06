package com.mindhub.homebanking1.services;

import com.mindhub.homebanking1.dtos.ClientDTO;
import com.mindhub.homebanking1.models.Client;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getClients();


    Client findById(Long id);


    ClientDTO getClientById(Long id);


    Client findByEmail (String email);


    ClientDTO getClientCurrentAll (String email);

    void clientAdd(Client client);

    void clientSave(Client client);
}
