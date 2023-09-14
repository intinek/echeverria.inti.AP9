package com.mindhub.homebanking1;

import com.mindhub.homebanking1.models.*;
import com.mindhub.homebanking1.repositories.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class RepositoriesTest {


    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TransactionRepository transactionRepository;



    @Test
    public void existLoans(){
        List<Loan> loans = loanRepository.findAll();
        assertThat(loans,is(not(empty())));

    }


    @Test
    public void existPersonalLoan(){
        List<Loan> loans = loanRepository.findAll();
        assertThat(loans, hasItem(hasProperty("name", is("Personal"))));

    }



    @Test
    public void existClients(){
        List <Client> clients = clientRepository.findAll();
        assertThat(clients, is(not(empty())));
    }
    @Test
    public void newClient(){
        Client client = new Client("Leon", "Trots", "elleon@mail.com", "myPassword");
        clientRepository.save(client);
        Client clientSaved = clientRepository.findById(client.getId()).orElse(null);
        assertThat(clientSaved, equalTo(client));
    }

    //test accounts
    @Test
    public void existAccount(){
        List<Account>accounts= accountRepository.findAll();
        assertThat(accounts, is(not(empty())));
    }

    @Test
    public void newAccount(){
        Account account = new Account("vin-1234", LocalDate.now(), 0.0);
        accountRepository.save(account);
        Account accountSaved = accountRepository.findByNumber(account.getNumber());
        assertThat(accountSaved, equalTo(account));
    }

    //test Cards
    @Test
    public void existCard(){
        List<Card> cards =cardRepository.findAll();
        assertThat(cards, is(not(empty())));
    }

    @Test
    public void newCard(){
        Client client = new Client("Carl", "Marx", "camarx@mail.com", "myPassword");
        clientRepository.save(client);
        Card card = new Card(client.toString(), CardType.CREDIT, CardColor.GOLD, "8888-8888-8888-8888", 333, LocalDate.now().plusYears(5), LocalDate.now());
        cardRepository.save(card);
        Card cardSaved = cardRepository.findById(card.getId()).orElse(null);
        assertThat(cardSaved, equalTo(card));
    }


    @Test
    public void existTransaction(){
        List<Transaction> transactions = transactionRepository.findAll();
        assertThat(transactions, is(not(empty())));
    }

    @Test
    public void newTransaction(){
        Transaction transaction = new Transaction(TransactionType.DEBIT, 75432.0, "debt payment");
        transactionRepository.save(transaction);
        Transaction transactionSaved = transactionRepository.findById(transaction.getId()).orElse(null);
        assertThat(transactionSaved, equalTo(transaction));

    }

}
