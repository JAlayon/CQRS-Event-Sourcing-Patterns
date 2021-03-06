package com.alayon.springbank.user.cmd.api.controllers;

import com.alayon.springbank.user.cmd.api.commands.UpdateUserCommand;
import com.alayon.springbank.user.core.dto.BaseResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/updateUser")
@PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
public class UpdateUserController {

    private final CommandGateway commandGateway;

    @Autowired
    public UpdateUserController(final CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> updateUser(
            @PathVariable("id") String id,
            @Valid
            @RequestBody UpdateUserCommand command){

        try{
            command.setId(id);
            commandGateway.send(command);
            return new ResponseEntity<>(new BaseResponse("User successfully registered"), HttpStatus.OK);
        }catch(Exception e){
            var safeErrorMessage = "Error while processing update user request for id: " + id ;
            System.out.println(e.toString());
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
