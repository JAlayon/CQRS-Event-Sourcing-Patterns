package com.alayon.springbank.bankacc.query.api.handlers;

import com.alayon.springbank.bankacc.core.events.AccountClosedEvent;
import com.alayon.springbank.bankacc.core.events.AccountOpenedEvent;
import com.alayon.springbank.bankacc.core.events.FundsDepositedEvent;
import com.alayon.springbank.bankacc.core.events.FundsWithdrawnEvent;
import com.alayon.springbank.bankacc.core.models.BankAccount;
import com.alayon.springbank.bankacc.query.api.repositories.AccountRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup("bankaccount-group")
public class AccountEventHandlerImpl implements AccountEventHandler {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountEventHandlerImpl(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @EventHandler
    @Override
    public void on(final AccountOpenedEvent event) {
        var bankAccount = BankAccount.builder()
                .id(event.getId())
                .accountHolderId(event.getAccountHolderId())
                .creationDate(event.getCreationDate())
                .accountType(event.getAccountType())
                .balance(event.getOpeningBalance())
                .build();
        accountRepository.save(bankAccount);
    }

    @EventHandler
    @Override
    public void on(final FundsDepositedEvent event) {
        var bankAccount = accountRepository.findById(event.getId());
        bankAccount.ifPresentOrElse(account -> {
            account.setBalance(event.getBalance());
            accountRepository.save(account);
        }, () -> {
            return;
        });
    }

    @EventHandler
    @Override
    public void on(final FundsWithdrawnEvent event) {
        var bankAccount = accountRepository.findById(event.getId());
        bankAccount.ifPresentOrElse(account -> {
            account.setBalance(event.getBalance());
            accountRepository.save(account);
        }, () -> {
            return;
        });
    }

    @EventHandler
    @Override
    public void on(final AccountClosedEvent event) {
        accountRepository.deleteById(event.getId());
    }
}
