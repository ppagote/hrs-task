package com.uk.hrstask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.uk.hrstask.exception.NotFoundException;
import com.uk.hrstask.exception.UserCheckOutException;
import com.uk.hrstask.model.InputUserDetails;
import com.uk.hrstask.model.UserDetails;
import com.uk.hrstask.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService mockUserService;

    @Test
    void testListCheckedInUsers() throws Exception {
        // Setup

        // Configure UserService.listAllCheckedInUsers(...).
        final List<UserDetails> userDetails =
                Arrays.asList(new UserDetails(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(),
                        null, false, 0, "firstname", "lastName",
                        "emailId"));
        when(mockUserService.listAllCheckedInUsers()).thenReturn(userDetails);

        // Run the test
        mockMvc.perform(get("/api/users/v1/listcheckedin")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].hasCheckedOut").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].hasCheckedOut").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].hasCheckedOut").value(false));
    }

    @Test
    void testListCheckedInUsers_UserServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUserService.listAllCheckedInUsers()).thenReturn(Collections.emptyList());

        // Run the test
        mockMvc.perform(get("/api/users/v1/listcheckedin")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
        //.andReturn().getResponse();

        // Verify the results
        /*assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(Collections.emptyList());*/
    }

    @Test
    void testListCheckedOutUsers() throws Exception {
        // Setup

        // Configure UserService.listAllCheckedOutUsers(...).
        final List<UserDetails> userDetails =
                Arrays.asList(new UserDetails(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(),
                        new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), true,
                        0, "firstname", "lastName", "emailId"));
        when(mockUserService.listAllCheckedOutUsers()).thenReturn(userDetails);

        // Run the test
        mockMvc.perform(get("/api/users/v1/listcheckedout")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].hasCheckedOut").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].hasCheckedOut").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].hasCheckedOut").value(true));


    }

    @Test
    void testListCheckedOutUsers_UserServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUserService.listAllCheckedOutUsers()).thenReturn(Collections.emptyList());

        // Run the test
        mockMvc.perform(get("/api/users/v1/listcheckedout")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }

    @Test
    void testGetUserDetails() throws Exception {
        // Setup

        // Configure UserService.getDetailsOfUser(...).
        final UserDetails userDetails =
                new UserDetails(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(),
                        null, false, 0, "firstname", "lastName",
                        "emailId");
        when(mockUserService.getDetailsOfUser(0L)).thenReturn(userDetails);

        // Run the test
        mockMvc.perform(get("/api/users/v1/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.hasCheckedOut").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.hasCheckedOut").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.hasCheckedOut").value(false));

    }

    @Test
    void testGetUserDetails_UserServiceThrowsNotFoundException() throws Exception {
        // Setup
        when(mockUserService.getDetailsOfUser(0L)).thenThrow(new NotFoundException("User not found with id:: 0"));

        // Run the test
        mockMvc.perform(get("/api/users/v1/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(mvcResult -> Assertions.assertTrue(mvcResult.getResolvedException() instanceof NotFoundException))
                .andExpect(mvcResult -> Assertions.assertTrue(
                        mvcResult.getResolvedException().getMessage().contains("User not found with id::")));
    }

    @Test
    void testCheckInUser() throws Exception {
        // Setup
        final InputUserDetails inputUserDetails = new InputUserDetails();
        inputUserDetails.setEmailId("emailId@gmail.com");
        inputUserDetails.setFirstName("firstname");
        inputUserDetails.setLastName("lastName");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(inputUserDetails);
        // Configure UserService.createUser(...).
        final UserDetails userDetails =
                new UserDetails(new GregorianCalendar().getTime(),
                        null, false, 0, "firstname", "lastName",
                        "emailId@gmail.com");


        when(mockUserService.createUser(any(UserDetails.class))).thenReturn(userDetails);

        // Run the test
        mockMvc.perform(post("/api/users/v1/")
                .content(requestJson).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.hasCheckedOut").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.hasCheckedOut").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.hasCheckedOut").value(false));

    }

    @Test
    void testUpdateParcelCountOfUser() throws Exception {
        // Setup

        // Configure UserService.updateParcelCount(...).
        final UserDetails userDetails =
                new UserDetails(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(),
                        null, false, 4, "firstname", "lastName",
                        "emailId");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(userDetails);

        when(mockUserService.updateParcelCount(eq(0L), any(UserDetails.class))).thenReturn(userDetails);

        // Run the test
        mockMvc.perform(put("/api/users/v1/{id}/updateparcel", 0)
                .content(requestJson).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.parcelCount").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.parcelCount").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.parcelCount").value(4));
    }

    @Test
    void testUpdateParcelCountOfUser_UserServiceThrowsNotFoundException() throws Exception {
        // Setup
        when(mockUserService.updateParcelCount(eq(0L), any(UserDetails.class)))
                .thenThrow(new NotFoundException("User not found with id:: 0"));

        // Run the test
        mockMvc.perform(put("/api/users/v1/{id}/updateparcel", 0)
                .content("{}").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(mvcResult -> Assertions.assertTrue(mvcResult.getResolvedException() instanceof NotFoundException))
                .andExpect(mvcResult -> Assertions.assertTrue(
                        mvcResult.getResolvedException().getMessage().contains("User not found with id::")));
    }

    @Test
    void testUpdateParcelCountOfUser_UserServiceThrowsUserCheckOutException() throws Exception {
        // Setup
        when(mockUserService.updateParcelCount(eq(0L), any(UserDetails.class)))
                .thenThrow(new UserCheckOutException("Cannot Accept Parcel as user has checked-out"));

        // Run the test
        mockMvc.perform(put("/api/users/v1/{id}/updateparcel", 0)
                .content("{}").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(mvcResult -> Assertions.assertTrue(mvcResult.getResolvedException() instanceof UserCheckOutException))
                .andExpect(mvcResult -> Assertions.assertEquals("Cannot Accept Parcel as user has checked-out",
                        mvcResult.getResolvedException().getMessage()));
    }

    @Test
    void testCheckOutUser() throws Exception {
        // Setup

        // Configure UserService.checkOutUser(...).
        final UserDetails userDetails =
                new UserDetails(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(),
                        new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(),
                        true, 0, "firstname", "lastName", "emailId");
        when(mockUserService.checkOutUser(0L)).thenReturn(userDetails);

        // Run the test
        mockMvc.perform(put("/api/users/v1/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.hasCheckedOut").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.parcelCount").value(0));
    }

    @Test
    void testCheckOutUser_UserServiceThrowsNotFoundException() throws Exception {
        // Setup
        when(mockUserService.checkOutUser(0L))
                .thenThrow(new NotFoundException("User not found with id:: 0"));

        // Run the test
        mockMvc.perform(put("/api/users/v1/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(mvcResult -> Assertions.assertTrue(mvcResult.getResolvedException() instanceof NotFoundException))
                .andExpect(mvcResult -> Assertions.assertTrue(
                        mvcResult.getResolvedException().getMessage().contains("User not found with id::")));
    }
}
