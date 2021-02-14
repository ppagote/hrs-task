package com.uk.hrstask.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class UserDetailsTest {

    private UserDetails userDetailsUnderTest;

    @BeforeEach
    void setUp() {
        userDetailsUnderTest =
                new UserDetails(new GregorianCalendar().getTime(),
                        null, false, 0, "firstname",
                        "lastName", "emailId");
    }

    @Test
    void testToString() {
        // Setup
        String details = "UserDetails{" +
                "id=" + 0L +
                ", checkInTime=" + new GregorianCalendar().getTime() +
                ", checkOutTime=" + null +
                ", hasCheckedOut=" + false +
                ", parcelCount=" + 0 +
                ", firstname='" + "firstname" + '\'' +
                ", lastName='" + "lastName" + '\'' +
                ", emailId='" + "emailId" + '\'' +
                '}';
        // Run the test
        final String result = userDetailsUnderTest.toString();

        // Verify the results
        assertThat(result).isEqualTo(details);
    }
}
