package com.alayon.springbank.bankacc.query.api.controllers;

import com.alayon.springbank.bankacc.query.api.dto.AccountLookupResponse;
import com.alayon.springbank.bankacc.query.api.dto.EqualityType;
import com.alayon.springbank.bankacc.query.api.queries.FindAccountByHolderIdQuery;
import com.alayon.springbank.bankacc.query.api.queries.FindAccountByIdQuery;
import com.alayon.springbank.bankacc.query.api.queries.FindAccountWithBalanceQuery;
import com.alayon.springbank.bankacc.query.api.queries.FindAllAccountsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bankAccountLookup")
public class AccountLookupController {

    private final QueryGateway queryGateway;

    @Autowired
    public AccountLookupController(final QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAllAccounts(){
        try{
            var query = new FindAllAccountsQuery();
            var response = queryGateway.query(query, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();
            if (response == null || response.getAccounts() == null){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(response);

        }catch(Exception e){
            var safeErrorMessage = "Failed to complete getAllAccounts request!";
            System.out.println(e.toString());
            return ResponseEntity
                    .internalServerError()
                    .body(new AccountLookupResponse(safeErrorMessage));
        }
    }

    @GetMapping("/byId/{id}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAccountById(@PathVariable("id") String id){
        try{
            var query = new FindAccountByIdQuery(id);
            var response = queryGateway.query(query, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();
            if (response == null || response.getAccounts() == null){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(response);

        }catch(Exception e){
            var safeErrorMessage = "Failed to complete getAccountById request!";
            System.out.println(e.toString());
            return ResponseEntity
                    .internalServerError()
                    .body(new AccountLookupResponse(safeErrorMessage));
        }
    }

    @GetMapping("/byHolderId/{accountHolderId}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAccountByHolderId(@PathVariable("id") String accountHolderId){
        try{
            var query = new FindAccountByHolderIdQuery(accountHolderId);
            var response = queryGateway.query(query, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();
            if (response == null || response.getAccounts() == null){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(response);

        }catch(Exception e){
            var safeErrorMessage = "Failed to complete getAccountByHolderId request!";
            System.out.println(e.toString());
            return ResponseEntity
                    .internalServerError()
                    .body(new AccountLookupResponse(safeErrorMessage));
        }
    }

    @GetMapping("/withBalance/{equalityType}/{balance}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAccountsWithBalance(
            @PathVariable("equalityType") EqualityType equalityType,
            @PathVariable("balance") Double balance){
        try{
            var query = new FindAccountWithBalanceQuery(equalityType, balance);
            var response = queryGateway.query(query, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();
            if (response == null || response.getAccounts() == null){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(response);

        }catch(Exception e){
            var safeErrorMessage = "Failed to complete getAccountsWithBalance request!";
            System.out.println(e.toString());
            return ResponseEntity
                    .internalServerError()
                    .body(new AccountLookupResponse(safeErrorMessage));
        }
    }
}
