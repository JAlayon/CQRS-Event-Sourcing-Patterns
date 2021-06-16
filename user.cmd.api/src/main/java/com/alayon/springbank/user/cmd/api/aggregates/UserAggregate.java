package com.alayon.springbank.user.cmd.api.aggregates;

import com.alayon.springbank.user.cmd.api.commands.RegisterUserCommand;
import com.alayon.springbank.user.cmd.api.commands.RemoveUserCommand;
import com.alayon.springbank.user.cmd.api.commands.UpdateUserCommand;
import com.alayon.springbank.user.cmd.api.security.PasswordEncoder;
import com.alayon.springbank.user.core.events.UserRegisteredEvent;
import com.alayon.springbank.user.core.events.UserRemovedEvent;
import com.alayon.springbank.user.core.events.UserUpdatedEvent;
import com.alayon.springbank.user.core.models.User;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@Aggregate
public class UserAggregate {
    @AggregateIdentifier
    private String id;
    private User user;

    private PasswordEncoder passwordEncoder;

    public UserAggregate(){
        initPasswordEncoder();
    }

    @CommandHandler
    public UserAggregate(RegisterUserCommand command){
        initPasswordEncoder();
        var newUser = command.getUser();
        newUser.setId(command.getId());
        var password = newUser.getAccount().getPassword();
        var hashedPassword = passwordEncoder.hashPassword(password);
        newUser.getAccount().setPassword(hashedPassword);

        var event = UserRegisteredEvent.builder()
                        .id(command.getId())
                        .user(newUser)
                        .build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdateUserCommand command){
        var updatedUser = command.getUser();
        updatedUser.setId(command.getId());
        var password = updatedUser.getAccount().getPassword();
        var hashedPassword = passwordEncoder.hashPassword(password);
        updatedUser.getAccount().setPassword(hashedPassword);

        var event = UserUpdatedEvent.builder()
                            .id(UUID.randomUUID().toString())
                            .user(updatedUser)
                            .build();
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(RemoveUserCommand command){
        var event = new UserRemovedEvent();
        event.setId(command.getId());

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserRegisteredEvent event){
        this.id = event.getId();
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserUpdatedEvent event){
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserRemovedEvent event){
        AggregateLifecycle.markDeleted();
    }

    private void initPasswordEncoder(){
        passwordEncoder = (p) -> {
            var encoder = new BCryptPasswordEncoder(12);
            var hashedPassword = encoder.encode(p);
            return hashedPassword;
        };
    }
}
