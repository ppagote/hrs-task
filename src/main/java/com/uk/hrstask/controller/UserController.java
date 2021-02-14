package com.uk.hrstask.controller;

import com.uk.hrstask.exception.ErrorDetails;
import com.uk.hrstask.exception.NotFoundException;
import com.uk.hrstask.exception.UserCheckOutException;
import com.uk.hrstask.interfaces.CheckInUserInfo;
import com.uk.hrstask.interfaces.UpdateParcelInfo;
import com.uk.hrstask.model.InputUserDetails;
import com.uk.hrstask.model.UserDetails;
import com.uk.hrstask.service.UserService;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/v1")
@Api(tags = {"Users"})
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/listcheckedin", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find all users which are checked-in and not checked out",
            notes = "Retrieving the collection of user", response = UserDetails[].class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = UserDetails[].class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetails.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorDetails.class)
    })
    public List<UserDetails> listCheckedInUsers() {
        return userService.listAllCheckedInUsers();
    }

    @GetMapping(value = "/listcheckedout", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find all users which are checked out",
            notes = "Retrieving the collection of user", response = UserDetails[].class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = UserDetails[].class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetails.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorDetails.class)
    })
    public List<UserDetails> listCheckedOutUsers() {
        return userService.listAllCheckedOutUsers();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find the user by ID",
            notes = "Retrieving the  user", response = UserDetails.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = UserDetails[].class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetails.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorDetails.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorDetails.class)
    })
    public UserDetails getUserDetails(
            @ApiParam(required = true, name = "id", value = "ID of the user you want to retrieve the details")
            @PathVariable(value = "id") long userId) throws NotFoundException {
        return userService.getDetailsOfUser(userId);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create user",
            notes = "Create a new user", response = UserDetails.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = UserDetails.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetails.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorDetails.class),
    })
    public UserDetails checkInUser(
            @ApiParam(required = true, name = "user", value = "Create user")
            @RequestBody @Validated(CheckInUserInfo.class) InputUserDetails inputUserDetails) {
        UserDetails userDetails = new UserDetails();
        userDetails.setLastName(inputUserDetails.getLastName());
        userDetails.setFirstName(inputUserDetails.getFirstName());
        userDetails.setEmailId(inputUserDetails.getEmailId());
        userDetails.setParcelCount(inputUserDetails.getParcelCount());

        return userService.createUser(userDetails);
    }

    @PutMapping(value = "/{id}/updateparcel", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update parcel status",
            notes = "Update the parcel status of checked-in user", response = UserDetails.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = UserDetails.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetails.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorDetails.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorDetails.class)
    })
    public UserDetails updateParcelCountOfUser(
            @ApiParam(required = true, name = "id", value = "ID of the user you want to retrieve the details")
            @PathVariable(value = "id") long userId,
            @ApiParam(required = true, name = "update", value = "update the parcel status")
            @RequestBody @Validated(UpdateParcelInfo.class) InputUserDetails inputUserDetails)
            throws NotFoundException, UserCheckOutException {
        UserDetails userDetails = new UserDetails();
        /*userDetails.setLastName(inputUserDetails.getLastName());
        userDetails.setFirstName(inputUserDetails.getFirstName());
        userDetails.setEmailId(inputUserDetails.getEmailId());*/
        userDetails.setParcelCount(inputUserDetails.getParcelCount());

        return userService.updateParcelCount(userId, userDetails);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "user checkout",
            notes = "Check out user and handover the parcel if any", response = UserDetails.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = UserDetails.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetails.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorDetails.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorDetails.class)
    })
    public UserDetails checkOutUser(
            @ApiParam(required = true, name = "id", value = "ID of the user you want to retrieve the details")
            @PathVariable(value = "id") long userId) throws NotFoundException {
        return userService.checkOutUser(userId);
    }

}
