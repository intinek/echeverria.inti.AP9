package com.mindhub.homebanking1;

import com.mindhub.homebanking1.models.*;
import com.mindhub.homebanking1.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class Homebanking1Application {
	@Autowired
	private PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(Homebanking1Application.class, args);

		System.out.println("Cvv generado: " + generationCvv());

	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository){
			return (args)->{


				Client client1 = new Client("Melba", "Morel", "melba@mindhub.com", passwordEncoder.encode("111"));
				clientRepository.save(client1);

				Client client2 = new Client("Inti", "Echeverria", "inti@mindhub.com", passwordEncoder.encode("222"));
				clientRepository.save(client2);

				Client clientAdmin = new Client("Nahuel", "Echeverria", "nahuel@mindhub.com", passwordEncoder.encode("333"));
				clientRepository.save(clientAdmin);

				Account account1 = new Account( "VIN001",LocalDate.now(), 4000);
				client1.addAccount(account1);
				accountRepository.save(account1);

				Account account2 = new Account( "VIN002",LocalDate.now().plusDays(1), 8000);
				client1.addAccount(account2);
				accountRepository.save(account2);


				Account account3 = new Account( "VIN003",LocalDate.now().plusDays(1), 7000);
				client2.addAccount(account3);
				accountRepository.save(account3);

				Transaction transaction1 = new Transaction(TransactionType.DEBIT, -80.000,"Transferecia");
				account1.addTransaction(transaction1);
				transactionRepository.save(transaction1);
				Transaction transaction2 = new Transaction(TransactionType.CREDIT, 200.000,"Deposito");
				account2.addTransaction(transaction2);
				transactionRepository.save(transaction2);


				Loan Hipotecario = new Loan("Hipotecario", 500000.0, List.of(12,24,36,48,60));
				loanRepository.save(Hipotecario);
				Loan Personal = new Loan("Personal", 1000000.0, List.of(6, 12, 24));
				loanRepository.save(Personal);
				Loan Automotriz = new Loan("Automotriz", 300000.0, List.of(6,12 ,24,36));
				loanRepository.save(Automotriz);


				ClientLoan Melba1 = new ClientLoan(400000.0,60);
				client1.addClientLoan(Melba1);
				Hipotecario.addClientLoan(Melba1);
				clientLoanRepository.save(Melba1);
				ClientLoan Melba2 = new ClientLoan(50000.0,12);
				client1.addClientLoan(Melba2);
				Personal.addClientLoan(Melba2);
				clientLoanRepository.save(Melba2);
				ClientLoan Inti1 = new ClientLoan(100000.0,24);
				client2.addClientLoan(Inti1);
				Automotriz.addClientLoan(Inti1);
				clientLoanRepository.save(Inti1);
				ClientLoan Inti2 = new ClientLoan(200000.0,36);
				client2.addClientLoan(Inti2);
				Personal.addClientLoan(Inti2);
				clientLoanRepository.save(Inti2);


				Card Card1 = new Card( client1.toString(), CardType.DEBIT, CardColor.GOLD,
						"6332-4252-3545-1224", generationCvv(), LocalDate.now(), LocalDate.now().plusYears(5));
				client1.addCard(Card1);
				cardRepository.save(Card1);
				Card Card2 = new Card( client1.toString(), CardType.CREDIT, CardColor.TITANIUM,
						"5824-1321-8574-0247", generationCvv(), LocalDate.now(), LocalDate.now().plusYears(5));
				client1.addCard(Card2);
				cardRepository.save(Card2);

				Card Card3 = new Card( client1.toString(), CardType.CREDIT, CardColor.SILVER,
						"2454-0445.4004-8844", generationCvv(), LocalDate.now(), LocalDate.now().plusYears(5));
				client1.addCard(Card3);
				cardRepository.save(Card3);




			};
	}
	public static int generationCvv(){
		Random random = new Random();
		int cvv = random.nextInt(900)+100;
		return cvv;
	}

}
