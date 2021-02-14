package com.uk.hrstask.model;

import com.uk.hrstask.interfaces.CheckInUserInfo;
import com.uk.hrstask.interfaces.UpdateParcelInfo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InputUserDetails {

    @NotNull(groups = UpdateParcelInfo.class)
    private int parcelCount;

    @NotBlank(message = "firstName is mandatory", groups = CheckInUserInfo.class)
    @Size(min = 3, max = 15, groups = CheckInUserInfo.class)
    @NotNull(groups = CheckInUserInfo.class)
    private String firstName;

    @NotBlank(message = "lastName is mandatory", groups = CheckInUserInfo.class)
    @Size(min = 3, max = 15, groups = CheckInUserInfo.class)
    @NotNull(groups = CheckInUserInfo.class)
    private String lastName;

    @NotBlank(message = "emailId is mandatory", groups = CheckInUserInfo.class)
    @Email(groups = CheckInUserInfo.class)
    @Size(min = 5, max = 30, groups = CheckInUserInfo.class)
    @NotNull(groups = CheckInUserInfo.class)
    private String emailId;

    public int getParcelCount() {
        return parcelCount;
    }

    public void setParcelCount(int parcelCount) {
        this.parcelCount = parcelCount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
