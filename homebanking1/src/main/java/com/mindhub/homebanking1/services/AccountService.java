package com.mindhub.homebanking1.services;

import com.mindhub.homebanking1.dtos.AccountDTO;
import com.mindhub.homebanking1.models.Account;

import java.util.List;

public interface AccountService {
    List<AccountDTO> getAccounts();

    Account findByID (Long id);

    Account findByNumber(String number);

    void accountSave(Account account);



}

