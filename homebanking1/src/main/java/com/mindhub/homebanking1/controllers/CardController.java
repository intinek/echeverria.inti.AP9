package com.mindhub.homebanking1.controllers;

import com.mindhub.homebanking1.dtos.CardDTO;
import com.mindhub.homebanking1.models.Card;
import com.mindhub.homebanking1.models.CardColor;
import com.mindhub.homebanking1.models.CardType;
import com.mindhub.homebanking1.models.Client;
import com.mindhub.homebanking1.services.CardService;
import com.mindhub.homebanking1.services.ClientService;
import com.mindhub.homebanking1.utils.CardUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private ClientService clientService;


    @GetMapping("/cards")
    public List<CardDTO> getCards() {
        return cardService.getCards();

    }


    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> createCard (Authentication authentication,
                                              @RequestParam CardType cardType,
                                              @RequestParam CardColor cardColor) {
        Client client = clientService.findByEmail(authentication.getName());

        if (cardType == null || cardColor == null) {
            return new ResponseEntity<>("Missing data", HttpStatus.NO_CONTENT);
        }
        Set<Card> cards = client.getCards();

        int cardLimit;
        if (cardType == CardType.CREDIT || cardType == CardType.DEBIT) {
            cardLimit = 3;

            long cardsSameType = cards.stream()
                    .filter(newCard -> newCard.getType() == cardType)
                    .count();
            if (cardsSameType >= cardLimit) {
                return new ResponseEntity<>("Max amount of card reached", HttpStatus.FORBIDDEN);
            }
            boolean colorUsed = cards.stream()
                    .anyMatch(card -> card.getType() == cardType && card.getColor() == cardColor);
            if (colorUsed) {
                return new ResponseEntity<>("Color already used", HttpStatus.BAD_REQUEST);
            }
        }
        String numberCard;
        do {
            numberCard = CardUtils.generateRandomCreditCardNumber();
        }
        while (cardService.findCardByNumber(numberCard) != null);
        int randomCvvNumber = CardUtils.generateRandomCVV();
        Card card = new Card(client.getFirstName(), cardType, cardColor, numberCard, randomCvvNumber, LocalDate.now(), LocalDate.now().plusYears(5));
        client.addCard(card);
        cardService.cardSave(card);
        return new ResponseEntity<>("Account created", HttpStatus.CREATED);
    }




}
