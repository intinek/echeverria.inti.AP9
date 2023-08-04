package com.mindhub.homebanking1;

import com.mindhub.homebanking1.models.Client;
import com.mindhub.homebanking1.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Homebanking1Application {

	public static void main(String[] args) {
		SpringApplication.run(Homebanking1Application.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository){
			return (args)->{


				clientRepository.save(new Client("Melba","Morel","melba@mindhub.com"));
				clientRepository.save(new Client("Inti","Echeverria","inti@mindhub.com"));


			};
	}

}
