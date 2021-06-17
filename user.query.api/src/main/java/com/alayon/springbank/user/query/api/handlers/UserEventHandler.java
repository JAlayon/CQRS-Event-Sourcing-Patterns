package com.alayon.springbank.user.query.api.handlers;

import com.alayon.springbank.user.core.events.UserRegisteredEvent;
import com.alayon.springbank.user.core.events.UserRemovedEvent;
import com.alayon.springbank.user.core.events.UserUpdatedEvent;

public interface UserEventHandler {

    public void on(UserRegisteredEvent event);
    public void on(UserUpdatedEvent event);
    public void on(UserRemovedEvent event);
}
