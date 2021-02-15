package com.uk.hrstask.service;

import com.uk.hrstask.exception.NotFoundException;
import com.uk.hrstask.exception.UserCheckOutException;
import com.uk.hrstask.model.InputUserDetails;
import com.uk.hrstask.model.UserDetails;
import com.uk.hrstask.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    private UserService userServiceUnderTest;

    @BeforeEach
    void setUp() {
        userServiceUnderTest = new UserService(mockUserRepository);
    }

    @Test
    void testListAllCheckedInUsers() {
        // Setup

        // Configure UserRepository.findByHasCheckedOutFalse(...).
        final List<UserDetails> userDetails =
                Arrays.asList(new UserDetails(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(),
                        null, false, 0, "firstname", "lastName",
                        "emailId"));
        when(mockUserRepository.findByHasCheckedOutFalse()).thenReturn(userDetails);

        // Run the test
        final List<UserDetails> result = userServiceUnderTest.listAllCheckedInUsers();

        // Verify the results
        assertEquals(userDetails, result);
    }

    @Test
    void testListAllCheckedInUsers_UserRepositoryReturnsNoItems() {
        // Setup
        when(mockUserRepository.findByHasCheckedOutFalse()).thenReturn(Collections.emptyList());

        // Run the test
        final List<UserDetails> result = userServiceUnderTest.listAllCheckedInUsers();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

   /* @Test
    void testListAllCheckedOutUsers() {
        // Setup

        // Configure UserRepository.findByHasCheckedOutTrue(...).
        final List<UserDetails> userDetails =
                Arrays.asList(new UserDetails(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(),
                        null, false, 0, "firstname", "lastName", "emailId"));
        when(mockUserRepository.findByHasCheckedOutTrue()).thenReturn(userDetails);

        // Run the test
        final List<UserDetails> result = userServiceUnderTest.listAllCheckedOutUsers();

        // Verify the results
        assertEquals(userDetails, result);
    }*/

   /* @Test
    void testListAllCheckedOutUsers_UserRepositoryReturnsNoItems() {
        // Setup
        when(mockUserRepository.findByHasCheckedOutTrue()).thenReturn(Collections.emptyList());

        // Run the test
        final List<UserDetails> result = userServiceUnderTest.listAllCheckedOutUsers();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }*/

    @Test
    void testGetDetailsOfUser() throws Exception {
        // Setup

        // Configure UserRepository.findById(...).
        final Optional<UserDetails> userDetails =
                Optional.of(new UserDetails(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(),
                        null, false, 0, "firstname", "lastName",
                        "emailId"));
        when(mockUserRepository.findById(0L)).thenReturn(userDetails);

        // Run the test
        final UserDetails result = userServiceUnderTest.getDetailsOfUser(0L);

        // Verify the results
        assertEquals(userDetails.get(), result);
    }

    @Test
    void testGetDetailsOfUser_ThrowsNotFoundException() {
        Exception exception = assertThrows(NotFoundException.class, () -> {
            userServiceUnderTest.getDetailsOfUser(1L);
        });

        String expectedMessage = "User not found with id:: ";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testCreateUser() throws Exception {
        // Setup
        final UserDetails userDetails =
                new UserDetails(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(),
                        null, false, 0, "firstname", "lastName", "emailId");

        // Configure UserRepository.save(...).
        when(mockUserRepository.save(any(UserDetails.class))).thenReturn(userDetails);

        // Run the test
        final UserDetails result = userServiceUnderTest.createUser(userDetails);

        // Verify the results
        assertEquals(userDetails, result);
    }

    @Test
    void testUpdateParcelCount() throws Exception {
        // Setup
        final InputUserDetails inputUserDetails = new InputUserDetails();
        inputUserDetails.setParcelCount(5);

        // Configure UserRepository.findById(...).
        final Optional<UserDetails> userDetails =
                Optional.of(new UserDetails(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(),
                        null, false, 0, "firstname", "lastName",
                        "emailId"));
        when(mockUserRepository.findById(0L)).thenReturn(userDetails);

        // Configure UserRepository.save(...).
        //final UserDetails userDetails1 = new UserDetails(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), false, 0, "firstname", "lastName", "emailId");
        when(mockUserRepository.save(any(UserDetails.class))).thenReturn(userDetails.get());

        // Run the test
        final UserDetails result = userServiceUnderTest.updateParcelCount(0L, inputUserDetails);

        // Verify the results
        assertEquals(userDetails.get(), result);
    }

    @Test
    void testUpdateParcelCount_ThrowsNotFoundException() {
        // Setup
        final InputUserDetails inputUserDetails = new InputUserDetails();
        inputUserDetails.setParcelCount(5);

        // Configure UserRepository.findById(...).
        final Optional<UserDetails> userDetails =
                Optional.of(new UserDetails(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(),
                        null, false, 0, "firstname", "lastName",
                        "emailId"));

        Exception exception = assertThrows(NotFoundException.class, () -> {
            userServiceUnderTest.updateParcelCount(1L, inputUserDetails);
        });

        String expectedMessage = "User not found with id:: ";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        /*when(mockUserRepository.findById(0L)).thenReturn(userDetails);

        // Configure UserRepository.save(...).
        final UserDetails userDetails1 = new UserDetails(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), false, 0, "firstname", "lastName", "emailId");
        when(mockUserRepository.save(any(UserDetails.class))).thenReturn(userDetails1);

        // Run the test
        assertThatThrownBy(() -> userServiceUnderTest.updateParcelCount(0L, inputUserDetails)).isInstanceOf(NotFoundException.class);
    */
    }

    @Test
    void testUpdateParcelCount_ThrowsUserCheckOutException() {
        final InputUserDetails inputUserDetails = new InputUserDetails();
        inputUserDetails.setParcelCount(5);

        // Configure UserRepository.findById(...).
        final Optional<UserDetails> userDetails =
                Optional.of(new UserDetails(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(),
                        new GregorianCalendar(2019, Calendar.JANUARY, 2).getTime(),
                        true, 0, "firstname", "lastName",
                        "emailId"));
        when(mockUserRepository.findById(0L)).thenReturn(userDetails);

        Exception exception = assertThrows(UserCheckOutException.class, () -> {
            userServiceUnderTest.updateParcelCount(0L, inputUserDetails);
        });

        String expectedMessage = "Cannot Accept Parcel as user has checked-out";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
        /*// Configure UserRepository.save(...).
        final UserDetails userDetails1 = new UserDetails(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), false, 0, "firstname", "lastName", "emailId");
        when(mockUserRepository.save(any(UserDetails.class))).thenReturn(userDetails1);

        // Run the test
        assertThatThrownBy(() -> userServiceUnderTest.updateParcelCount(0L, inputUserDetails)).isInstanceOf(UserCheckOutException.class);
    */
    }


    @Test
    void testCheckOutUser() throws Exception {
        // Setup

        // Configure UserRepository.findById(...).
        final Optional<UserDetails> userDetails =
                Optional.of(new UserDetails(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(),
                        null, false, 0, "firstname", "lastName",
                        "emailId"));
        when(mockUserRepository.findById(0L)).thenReturn(userDetails);

        // Configure UserRepository.save(...).
        //final UserDetails userDetails1 = new UserDetails(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), false, 0, "firstname", "lastName", "emailId");
        when(mockUserRepository.save(any(UserDetails.class))).thenReturn(userDetails.get());

        // Run the test
        final UserDetails result = userServiceUnderTest.checkOutUser(0L);

        // Verify the results
        assertEquals(userDetails.get(), result);
    }

    @Test
    void testCheckOutUser_ThrowsNotFoundException() {
        // Setup

        Exception exception = assertThrows(NotFoundException.class, () -> {
            userServiceUnderTest.getDetailsOfUser(1L);
        });

        String expectedMessage = "User not found with id:: ";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}
