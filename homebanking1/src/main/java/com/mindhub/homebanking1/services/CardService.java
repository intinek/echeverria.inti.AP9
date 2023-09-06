package com.mindhub.homebanking1.services;

import com.mindhub.homebanking1.dtos.CardDTO;
import com.mindhub.homebanking1.models.Card;

import java.util.List;

public interface CardService {
    List<CardDTO> getCards();

    Card findCardByNumber(String randomNumber);

    void cardSave(Card card);
}
