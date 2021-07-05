package com.alayon.springbank.bankacc.query.api.handlers;

import com.alayon.springbank.bankacc.core.models.BankAccount;
import com.alayon.springbank.bankacc.query.api.dto.AccountLookupRespnse;
import com.alayon.springbank.bankacc.query.api.queries.FindAccountByHolderIdQuery;
import com.alayon.springbank.bankacc.query.api.queries.FindAccountByIdQuery;
import com.alayon.springbank.bankacc.query.api.queries.FindAccountWithBalanceQuery;
import com.alayon.springbank.bankacc.query.api.queries.FindAllAccountsQuery;
import com.alayon.springbank.bankacc.query.api.repositories.AccountRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountQueryHandlerImpl implements AccountQueryHandler {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountQueryHandlerImpl(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @QueryHandler
    @Override
    public AccountLookupRespnse findAccountById(final FindAccountByIdQuery query) {
        var bankAccount = accountRepository.findById(query.getId());
        var response = bankAccount.isPresent()
                ? new AccountLookupRespnse("Bank Account Successfully Returned!", bankAccount.get())
                : new AccountLookupRespnse("No Account Found For ID: " + query.getId());
        return response;
    }

    @QueryHandler
    @Override
    public AccountLookupRespnse findAccountByHolderId(final FindAccountByHolderIdQuery query) {
        var bankAccount = accountRepository.findByAccountHolderId(query.getAccountHolderId());
        var response = bankAccount.isPresent()
                ? new AccountLookupRespnse("Bank Account Successfully Returned!", bankAccount.get())
                : new AccountLookupRespnse("No Account Found For Holder ID: " + query.getAccountHolderId());
        return response;
    }

    @QueryHandler
    @Override
    public AccountLookupRespnse findAllAccounts(final FindAllAccountsQuery query) {
        var bankAccountIterator = accountRepository.findAll();
        if(!bankAccountIterator.iterator().hasNext()){
            return new AccountLookupRespnse("No Bank Account Were Founded!");
        }
        var bankAccounts = new ArrayList<BankAccount>();
        bankAccountIterator.forEach(i -> bankAccounts.add(i));
        var count = bankAccounts.size();
        return new AccountLookupRespnse("Successfully Returned " + count + " Bank Account(s)!");
    }

    @QueryHandler
    @Override
    public AccountLookupRespnse findAccountWithBalance(final FindAccountWithBalanceQuery query) {
        var equalityType = query.getEqualityType();
        List<BankAccount> bankAccounts;
        switch (equalityType){
            case GREATER_THAN:
                bankAccounts = accountRepository.findByBalanceGreaterThan(query.getBalance());
                break;
            case LESS_THAN:
                bankAccounts = accountRepository.findByBalanceLessThan(query.getBalance());
                break;
            default:
                throw new RuntimeException("Equality type incorrect!!");
        }
        var response = bankAccounts != null && bankAccounts.size() > 0
                        ? new AccountLookupRespnse("Successfully Returned " + bankAccounts.size() + " Bank Account(s)!", bankAccounts)
                        : new AccountLookupRespnse("No Bank Account Were Founded!");

        return response;
    }
}
