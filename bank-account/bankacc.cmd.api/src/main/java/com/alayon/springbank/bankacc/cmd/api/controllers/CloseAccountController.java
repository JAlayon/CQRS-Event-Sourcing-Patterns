package com.alayon.springbank.bankacc.cmd.api.controllers;

import com.alayon.springbank.bankacc.cmd.api.commands.CloseAccountCommand;
import com.alayon.springbank.bankacc.core.dto.BaseResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/closeBankAccount")
public class CloseAccountController {
    private final CommandGateway commandGateway;

    @Autowired
    public CloseAccountController(final CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> closeAccount
            (@PathVariable("id") String id){

        try{
            var command = CloseAccountCommand.builder()
                    .id(id)
                    .build();
            commandGateway.send(command);
            return ResponseEntity.ok(new BaseResponse("Bank Account successfully closed!"));
        }catch(Exception e){
            var safeErrorMessage = "Error while processing request to close bank account for id: "
                    + id;
            System.out.println(e.toString());
            return ResponseEntity.internalServerError()
                    .body(new BaseResponse(safeErrorMessage));
        }
    }
}
