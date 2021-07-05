package com.alayon.springbank.bankacc.query.api.handlers;

import com.alayon.springbank.bankacc.query.api.dto.AccountLookupResponse;
import com.alayon.springbank.bankacc.query.api.queries.FindAccountByHolderIdQuery;
import com.alayon.springbank.bankacc.query.api.queries.FindAccountByIdQuery;
import com.alayon.springbank.bankacc.query.api.queries.FindAccountWithBalanceQuery;
import com.alayon.springbank.bankacc.query.api.queries.FindAllAccountsQuery;

public interface AccountQueryHandler {
    AccountLookupResponse findAccountById(FindAccountByIdQuery query);
    AccountLookupResponse findAccountByHolderId(FindAccountByHolderIdQuery query);
    AccountLookupResponse findAllAccounts(FindAllAccountsQuery query);
    AccountLookupResponse findAccountWithBalance(FindAccountWithBalanceQuery query);
}
