package com.mindhub.homebanking1.services.implement;

import com.mindhub.homebanking1.dtos.ClientDTO;
import com.mindhub.homebanking1.models.Client;
import com.mindhub.homebanking1.repositories.ClientRepository;
import com.mindhub.homebanking1.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ClientServiceImplement implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<ClientDTO> getClients() {
        return  clientRepository.findAll()
                .stream()
                .map(currentClient -> new ClientDTO(currentClient))
                .collect(toList());
    }


    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public ClientDTO getClientById(Long id) {
        return new ClientDTO(this.findById(id));
    }


    @Override
    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);

    }

    @Override
    public ClientDTO getClientCurrentAll(String email) {
        return new ClientDTO(this.findByEmail(email));
    }


    @Override
    public void clientAdd(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void clientSave(Client client) {
        clientRepository.save(client);
    }


}
