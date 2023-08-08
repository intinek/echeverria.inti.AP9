package com.mindhub.homebanking1;

import com.mindhub.homebanking1.models.Account;
import com.mindhub.homebanking1.models.Client;
import com.mindhub.homebanking1.repositories.AccountRepository;
import com.mindhub.homebanking1.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class Homebanking1Application {

	public static void main(String[] args) {
		SpringApplication.run(Homebanking1Application.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository){
			return (args)->{


				Client client1 = new Client("Melba", "Morel", "melba@mindhub.com");
				clientRepository.save(client1);

				Client client2 = new Client("Inti", "Echeverria", "inti@mindhub.com");
				clientRepository.save(client2);

				Account account1 = new Account( client1, "VIN001",LocalDate.now(), 4000);
				accountRepository.save(account1);

				Account account2 = new Account(client1, "VIN002",LocalDate.now().plusDays(1), 8000);
				client1.addAccount(account2);
				accountRepository.save(account2);


				Account account3 = new Account(client2, "VIN003",LocalDate.now().plusDays(1), 7000);
				client2.addAccount(account3);
				accountRepository.save(account3);
			};
	}

}
