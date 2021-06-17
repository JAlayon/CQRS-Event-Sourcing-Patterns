package com.alayon.springbank.user.core.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    @Size(min = 2, message = "Username must have a minimum of 2 characters")
    private String username;

    @Size(min = 7, message = "Password must have a minimum of 7 characters")
    private String password;

    @NotNull(message = "You should specify at least 1 role")
    private List<Role> roles;
}
