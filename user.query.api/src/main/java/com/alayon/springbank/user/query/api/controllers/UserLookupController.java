package com.alayon.springbank.user.query.api.controllers;

import com.alayon.springbank.user.query.api.dto.UserLookupResponse;
import com.alayon.springbank.user.query.api.queries.FindAllUsersQuery;
import com.alayon.springbank.user.query.api.queries.FindUserByIdQuery;
import com.alayon.springbank.user.query.api.queries.SearchUsersQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/userLookup")
public class UserLookupController {

    private final QueryGateway queryGateway;

    @Autowired
    public UserLookupController(final QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public ResponseEntity<UserLookupResponse> getAllUsers(){
        try{
            var query = new FindAllUsersQuery();
            var response
                    = queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();

            if (response == null || response.getUsers() == null)
                return ResponseEntity.noContent().build();

            return ResponseEntity.ok(response);
        }catch(Exception e){
            var safeErrorMessage = "Failed to complete Get All Users request";
            System.out.println(e.toString());
            return ResponseEntity.internalServerError().body(new UserLookupResponse(safeErrorMessage));
        }
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<UserLookupResponse> getUserById(@PathVariable("id") String id){
        try{
            var query = new FindUserByIdQuery(id);
            var response
                    = queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();

            if (response == null || response.getUsers() == null)
                return ResponseEntity.noContent().build();

            return ResponseEntity.ok(response);
        }catch(Exception e){
            var safeErrorMessage = "Failed to complete Get User By Id request";
            System.out.println(e.toString());
            return ResponseEntity.internalServerError().body(new UserLookupResponse(safeErrorMessage));
        }
    }

    @GetMapping("/byFilter/{filter}")
    public ResponseEntity<UserLookupResponse> searchUserByFilter(@PathVariable("filter") String filter){
        try{
            var query = new SearchUsersQuery(filter);
            var response
                    = queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();

            if (response == null || response.getUsers() == null)
                return ResponseEntity.noContent().build();

            return ResponseEntity.ok(response);
        }catch(Exception e){
            var safeErrorMessage = "Failed to complete User Search request";
            System.out.println(e.toString());
            return ResponseEntity.internalServerError().body(new UserLookupResponse(safeErrorMessage));
        }
    }


}
