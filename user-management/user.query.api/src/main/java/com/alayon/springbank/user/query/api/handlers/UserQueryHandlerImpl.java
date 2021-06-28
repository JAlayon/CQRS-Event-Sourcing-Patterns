package com.alayon.springbank.user.query.api.handlers;

import com.alayon.springbank.user.core.models.User;
import com.alayon.springbank.user.query.api.dto.UserLookupResponse;
import com.alayon.springbank.user.query.api.queries.FindAllUsersQuery;
import com.alayon.springbank.user.query.api.queries.FindUserByIdQuery;
import com.alayon.springbank.user.query.api.queries.SearchUsersQuery;
import com.alayon.springbank.user.query.api.repositories.UserRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserQueryHandlerImpl implements UserQueryHandler{

    private final UserRepository userRepository;

    @Autowired
    public UserQueryHandlerImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @QueryHandler
    @Override
    public UserLookupResponse getAllUsers(final FindAllUsersQuery query) {
        var users = new ArrayList<>(userRepository.findAll());
        return new UserLookupResponse(users);
    }

    @QueryHandler
    @Override
    public UserLookupResponse getUserById(final FindUserByIdQuery query) {
        var user = userRepository.findById(query.getId());
        return user.isPresent() ? new UserLookupResponse(user.get()) : null;
    }

    @QueryHandler
    @Override
    public UserLookupResponse searchUsers(final SearchUsersQuery query) {
        var users = new ArrayList<User>(userRepository.findByFilterRegex(query.getFilter()));
        return new UserLookupResponse(users);
    }
}
