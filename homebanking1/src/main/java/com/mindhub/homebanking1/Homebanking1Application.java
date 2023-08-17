package com.mindhub.homebanking1;

import com.mindhub.homebanking1.models.*;
import com.mindhub.homebanking1.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class Homebanking1Application {

	public static void main(String[] args) {
		SpringApplication.run(Homebanking1Application.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository){
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

				Transaction transaction1 = new Transaction(account1, TransactionType.DEBIT, -80.000,"Transferecia",LocalDate.now());
				account1.addTransaction(transaction1);
				transactionRepository.save(transaction1);
				Transaction transaction2 = new Transaction(account2, TransactionType.CREDIT, 200.000,"Deposito",LocalDate.now());
				account2.addTransaction(transaction2);
				transactionRepository.save(transaction2);


				Loan Hipotecario = new Loan("Hipotecario", 500000.0, List.of(12,24,36,48,60));
				loanRepository.save(Hipotecario);
				Loan Personal = new Loan("Personal", 1000000.0, List.of(6, 12, 24));
				loanRepository.save(Personal);
				Loan Automotriz = new Loan("Automotriz", 300000.0, List.of(6,12 ,24,36));
				loanRepository.save(Automotriz);


				ClientLoan Melba1 = new ClientLoan(400000.0, List.of(60), client1, Hipotecario);
				clientLoanRepository.save(Melba1);
				ClientLoan Melba2 = new ClientLoan(50000.0, List.of(12),client1, Personal);
				clientLoanRepository.save(Melba2);
				ClientLoan Inti1 = new ClientLoan(100000.0, List.of(24), client2, Personal);
				clientLoanRepository.save(Inti1);
				ClientLoan Inti2 = new ClientLoan(200000.0, List.of(36), client2, Automotriz);
				clientLoanRepository.save(Inti2);


				Card Card1 = new Card(client1, client1.toString(), CardType.DEBIT, CardColor.GOLD,
						"6332-4252-3545-1224", 242, LocalDate.now(), LocalDate.now().plusYears(5));
				cardRepository.save(Card1);
				Card Card2 = new Card(client1, client1.toString(), CardType.CREDIT, CardColor.TITANIUM,
						"5824-1321-8574-0247", 367, LocalDate.now(), LocalDate.now().plusYears(5));
				cardRepository.save(Card2);

				Card Card3 = new Card(client1, client1.toString(), CardType.CREDIT, CardColor.SILVER,
						"2454-0445.4004-8844", 248, LocalDate.now(), LocalDate.now().plusYears(5));
				cardRepository.save(Card3);




			};
	}

}
