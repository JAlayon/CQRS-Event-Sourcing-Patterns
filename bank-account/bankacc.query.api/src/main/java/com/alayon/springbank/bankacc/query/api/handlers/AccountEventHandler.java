package com.alayon.springbank.bankacc.query.api.handlers;

import com.alayon.springbank.bankacc.core.events.AccountClosedEvent;
import com.alayon.springbank.bankacc.core.events.AccountOpenedEvent;
import com.alayon.springbank.bankacc.core.events.FundsDepositedEvent;
import com.alayon.springbank.bankacc.core.events.FundsWithdrawnEvent;

public interface AccountEventHandler {
    void on(AccountOpenedEvent event);
    void on(FundsDepositedEvent event);
    void on(FundsWithdrawnEvent event);
    void on(AccountClosedEvent event);
}
