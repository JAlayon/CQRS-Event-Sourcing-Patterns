package com.alayon.springbank.user.cmd.api.dto;

public class RegisterUserResponse extends BaseResponse{

    private String id;

    public RegisterUserResponse(final String message) {
        super(message);
    }
}
