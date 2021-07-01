package com.alayon.springbank.bankacc.query.api.repositories;

import com.alayon.springbank.bankacc.core.models.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<BankAccount, String> {
}
