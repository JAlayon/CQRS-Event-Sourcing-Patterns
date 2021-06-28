package com.alayon.springbank.user.query.api.handlers;

import com.alayon.springbank.user.core.events.UserRegisteredEvent;
import com.alayon.springbank.user.core.events.UserRemovedEvent;
import com.alayon.springbank.user.core.events.UserUpdatedEvent;
import com.alayon.springbank.user.query.api.repositories.UserRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup("user-group")
public class UserEventHandlerImpl implements UserEventHandler{

    private final UserRepository userRepository;

    @Autowired
    public UserEventHandlerImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @EventHandler
    @Override
    public void on(final UserRegisteredEvent event) {
        userRepository.save(event.getUser());
    }

    @EventHandler
    @Override
    public void on(final UserUpdatedEvent event) {
        userRepository.save(event.getUser());
    }

    @EventHandler
    @Override
    public void on(final UserRemovedEvent event) {
        userRepository.deleteById(event.getId());
    }
}
