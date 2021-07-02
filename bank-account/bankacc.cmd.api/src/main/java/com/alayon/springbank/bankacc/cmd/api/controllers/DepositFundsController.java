package com.alayon.springbank.bankacc.cmd.api.controllers;

import com.alayon.springbank.bankacc.cmd.api.commands.DepositFundsCommand;
import com.alayon.springbank.bankacc.core.dto.BaseResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/depositFunds")
public class DepositFundsController {

    private final CommandGateway commandGateway;

    @Autowired
    public DepositFundsController(final CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping(path="/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> depositFunds(
            @PathVariable("id") String id,
            @Valid
            @RequestBody
            DepositFundsCommand command){

        try {
            command.setId(id);
            commandGateway.send(command);
            return ResponseEntity.ok(new BaseResponse("Funds successfully deposited"));
        }catch (Exception e){
            var safeErrorMessage = "Error while processing request to deposit funds into bank account for id: "
                    + id;
            System.out.println(e.toString());
            return ResponseEntity.internalServerError()
                    .body(new BaseResponse(safeErrorMessage));
        }
    }
}
