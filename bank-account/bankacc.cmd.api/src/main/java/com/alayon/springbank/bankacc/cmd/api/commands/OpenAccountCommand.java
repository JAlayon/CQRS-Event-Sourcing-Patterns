package com.alayon.springbank.bankacc.cmd.api.commands;

import com.alayon.springbank.bankacc.core.models.AccountType;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class OpenAccountCommand {

    @TargetAggregateIdentifier
    private String id;

    @NotNull(message = "No account holder ID was supplied")
    private String accountHolderId;

    @NotNull(message = "No account type was supplied")
    private AccountType accountType;

    @Min(value = 50,message = "Opening Balance must be at least $50")
    private double openingBalance;
}
