package com.alayon.springbank.user.query.api.handlers;

import com.alayon.springbank.user.query.api.dto.UserLookupResponse;
import com.alayon.springbank.user.query.api.queries.FindAllUsersQuery;
import com.alayon.springbank.user.query.api.queries.FindUserByIdQuery;
import com.alayon.springbank.user.query.api.queries.SearchUsersQuery;

public interface UserQueryHandler {

    UserLookupResponse getAllUsers(FindAllUsersQuery query);
    UserLookupResponse getUserById(FindUserByIdQuery query);
    UserLookupResponse searchUsers(SearchUsersQuery query);
}
