package com.alayon.springbank.bankacc.cmd.api.commands;

import com.alayon.springbank.bankacc.core.models.AccountType;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class OpenAccountCommand {

    @TargetAggregateIdentifier
    private String id;

    private String account;

    private AccountType accountType;

    private double openingBalance;
}
