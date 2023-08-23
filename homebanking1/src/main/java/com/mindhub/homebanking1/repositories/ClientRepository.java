package com.mindhub.homebanking1.repositories;

import com.mindhub.homebanking1.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.core.Authentication;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);

}
