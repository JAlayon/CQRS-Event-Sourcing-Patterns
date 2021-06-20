package com.alayon.springbank.user.query.api.dto;

import com.alayon.springbank.user.core.dto.BaseResponse;
import com.alayon.springbank.user.core.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserLookupResponse extends BaseResponse {
    private List<User> users;

    public UserLookupResponse(final String message){
        super(message);
    }

    public UserLookupResponse(final String message, final User user) {
        super(message);
        this.users = new ArrayList<>();
        this.users.add(user);

    }

    public UserLookupResponse(final User user) {
        super(null);
        this.users = new ArrayList<>();
        this.users.add(user);
    }

    public UserLookupResponse(final List<User> users){
        super(null);
        this.users = users;
    }
    public List<User> getUsers(){
        return users;
    }

    public void setUsers(List<User> users){
        this.users = users;
    }
}
