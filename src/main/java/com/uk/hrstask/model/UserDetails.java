package com.uk.hrstask.model;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "userdetails")
@ApiModel(description = "Details about the users")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @CreationTimestamp
    @Column(name = "checkin_time", nullable = false)
    private Date checkInTime;
    @Column(name = "checkout_time")
    private Date checkOutTime;
    @Column(name = "has_checkout", columnDefinition = "boolean default false", nullable = false)
    private boolean hasCheckedOut;
    @Column(name = "parcel_count", columnDefinition = "int default 0", nullable = false)
    private int parcelCount;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email_address", nullable = false)
    private String emailId;

    public UserDetails(Date checkInTime, Date checkOutTime, boolean hasCheckedOut, int parcelCount,
                       String firstName, String lastName, String emailId) {
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.hasCheckedOut = hasCheckedOut;
        this.parcelCount = parcelCount;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
    }

    public UserDetails() {

    }

    public long getId() {
        return id;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }


    public boolean isHasCheckedOut() {
        return hasCheckedOut;
    }

    public void setHasCheckedOut(boolean hasCheckedOut) {
        this.hasCheckedOut = hasCheckedOut;
    }


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

    @Override
    public String toString() {
        return "UserDetails{" +
                "id=" + id +
                ", checkInTime=" + checkInTime +
                ", checkOutTime=" + checkOutTime +
                ", hasCheckedOut=" + hasCheckedOut +
                ", parcelCount=" + parcelCount +
                ", firstname='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}
