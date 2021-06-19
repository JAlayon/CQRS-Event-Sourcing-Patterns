package com.alayon.springbank.user.query.api.dto;

import com.alayon.springbank.user.core.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UserLookupResponse {
    private List<User> users;

    public UserLookupResponse(final User user) {
        this.users = new ArrayList<>();
        this.users.add(user);
    }
}
