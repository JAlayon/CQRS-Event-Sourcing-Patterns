package com.alayon.springbank.bankacc.query.api.dto;

import com.alayon.springbank.bankacc.core.dto.BaseResponse;
import com.alayon.springbank.bankacc.core.models.BankAccount;

import java.util.ArrayList;
import java.util.List;

public class AccountLookupResponse extends BaseResponse {

    private List<BankAccount> accounts;

    public AccountLookupResponse(final String message) {
        super(message);
    }

    public AccountLookupResponse(final String message, final List<BankAccount> accounts) {
        super(message);
        this.accounts = accounts;
    }

    public AccountLookupResponse(final String message, final BankAccount account) {
        super(message);
        this.accounts = new ArrayList<>();
        this.accounts.add(account);
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(final List<BankAccount> accounts) {
        this.accounts = accounts;
    }
}
