package com.uk.hrstask.service;

import com.uk.hrstask.exception.NotFoundException;
import com.uk.hrstask.exception.UserCheckOutException;
import com.uk.hrstask.model.InputUserDetails;
import com.uk.hrstask.model.UserDetails;
import com.uk.hrstask.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * List users details which are checked in and has not checkout out
     *
     * @return List
     */
    public List<UserDetails> listAllCheckedInUsers() {
        return userRepository.findByHasCheckedOutFalse();
    }

    /* *//**
     * List users details which are checkout out
     *
     * @return List
     *//*
    public List<UserDetails> listAllCheckedOutUsers() {
        return userRepository.findByHasCheckedOutTrue();
    }
*/

    /**
     * Get the details of a User by Id
     *
     * @param userId unique user id
     * @return UserDetails Object
     * @throws NotFoundException User Not found Exception
     */
    public UserDetails getDetailsOfUser(long userId) throws NotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id:: " + userId));
    }

    /**
     * Create a new user with details when user checks-in
     *
     * @param userDetails UserDetails Object
     * @return UserDetails Object
     */
    public UserDetails createUser(UserDetails userDetails) {
        return userRepository.save(userDetails);
    }

    /**
     * Logic to accept the parcel
     *
     * @param userId           unique user id
     * @param inputUserDetails InputUserDetails Object
     * @return UserDetails Object
     * @throws NotFoundException     User Not found Exception
     * @throws UserCheckOutException User has checkout Exception
     */
    public UserDetails updateParcelCount(long userId, InputUserDetails inputUserDetails)
            throws NotFoundException, UserCheckOutException {
        UserDetails userDetails = getDetailsOfUser(userId);

        if (userDetails.isHasCheckedOut()) {
            throw new UserCheckOutException("Cannot Accept Parcel as user has checked-out");
        }

        userDetails.setParcelCount(inputUserDetails.getParcelCount());
        /*userDetails.setCheckInTime(inputUserDetails.getCheckInTime());
        userDetails.setFirstName(inputUserDetails.getFirstName());
        userDetails.setEmailId(inputUserDetails.getEmailId());
        userDetails.setLastName(inputUserDetails.getLastName());*/
        return userRepository.save(userDetails);
    }

    /**
     * Update user details when user checkOut
     *
     * @param userId unique user id
     * @return UserDetails Object
     * @throws NotFoundException User Not found Exception
     */
    public UserDetails checkOutUser(long userId) throws NotFoundException {
        UserDetails userDetails = getDetailsOfUser(userId);

        userDetails.setParcelCount(0);
        userDetails.setCheckOutTime(new Date());
        userDetails.setHasCheckedOut(true);
        return userRepository.save(userDetails);
    }
}
