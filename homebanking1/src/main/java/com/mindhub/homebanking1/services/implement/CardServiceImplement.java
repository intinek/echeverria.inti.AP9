package com.mindhub.homebanking1.services.implement;

import com.mindhub.homebanking1.dtos.CardDTO;
import com.mindhub.homebanking1.models.Card;
import com.mindhub.homebanking1.repositories.CardRepository;
import com.mindhub.homebanking1.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardServiceImplement implements CardService {

    @Autowired
    private CardRepository cardRepository;

    //Trae lista de CardsDto
    @Override
    public List<CardDTO> getCards() {
        return cardRepository.findAll().stream()
                .map(currentCard -> new CardDTO(currentCard))
                .collect(Collectors.toList());
    }

    //busca por numero de tarjeta "card"
    @Override
    public Card findCardByNumber(String randomNumber) {
        return cardRepository.findCardByNumber(randomNumber);
    }

    //guardo nuevo registro en tarjeta
    @Override
    public void cardSave(Card card) {
        cardRepository.save(card);
    }


}
