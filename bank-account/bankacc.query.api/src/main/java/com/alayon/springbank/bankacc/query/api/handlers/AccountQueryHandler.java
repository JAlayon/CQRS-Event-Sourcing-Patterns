package com.alayon.springbank.bankacc.query.api.handlers;

import com.alayon.springbank.bankacc.query.api.dto.AccountLookupRespnse;
import com.alayon.springbank.bankacc.query.api.queries.FindAccountByHolderIdQuery;
import com.alayon.springbank.bankacc.query.api.queries.FindAccountByIdQuery;
import com.alayon.springbank.bankacc.query.api.queries.FindAccountWithBalanceQuery;
import com.alayon.springbank.bankacc.query.api.queries.FindAllAccountsQuery;

public interface AccountQueryHandler {
    AccountLookupRespnse findAccountById(FindAccountByIdQuery query);
    AccountLookupRespnse findAccountByHolderId(FindAccountByHolderIdQuery query);
    AccountLookupRespnse findAllAccounts(FindAllAccountsQuery query);
    AccountLookupRespnse findAccountWithBalance(FindAccountWithBalanceQuery query);
}
