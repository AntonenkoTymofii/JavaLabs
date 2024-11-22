package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LimitedLiftsSkiPassTest {

    private LimitedLiftsSkiPass limitedLiftsSkiPass;

    @BeforeEach
    void setUp() {
        // Initialize LimitedLiftsSkiPass with a valid date range and a set number of lifts
        limitedLiftsSkiPass = new LimitedLiftsSkiPass(SkiPassType.LIFTS_LIMITED, LocalDate.now(), LocalDate.now().plusDays(1), 3);
    }

    @Test
    void testPass_WithLiftsRemaining() {
        // Arrange & Act
        boolean result1 = limitedLiftsSkiPass.pass();
        boolean result2 = limitedLiftsSkiPass.pass();
        boolean result3 = limitedLiftsSkiPass.pass();

        // Assert
        assertTrue(result1, "First pass should be allowed.");
        assertTrue(result2, "Second pass should be allowed.");
        assertTrue(result3, "Third pass should be allowed.");
        assertEquals(0, limitedLiftsSkiPass.getLifts(), "There should be no lifts remaining.");
    }

    @Test
    void testPass_WithNoLiftsRemaining() {
        // Arrange
        limitedLiftsSkiPass.pass(); // 1st pass
        limitedLiftsSkiPass.pass(); // 2nd pass
        limitedLiftsSkiPass.pass(); // 3rd pass

        // Act
        boolean result = limitedLiftsSkiPass.pass(); // 4th attempt

        // Assert
        assertFalse(result, "Pass should be denied when no lifts are remaining.");
    }

    @Test
    void testPass_ExpiredSkiPass() {
        // Arrange
        LimitedLiftsSkiPass expiredSkiPass = new LimitedLiftsSkiPass(SkiPassType.LIFTS_LIMITED, LocalDate.now().minusDays(3), LocalDate.now().minusDays(1), 5);

        // Act
        boolean result = expiredSkiPass.pass();

        // Assert
        assertFalse(result, "Pass should be denied for an expired ski pass.");
    }

    @Test
    void testSetLifts() {
        // Act
        limitedLiftsSkiPass.setLifts(10);

        // Assert
        assertEquals(10, limitedLiftsSkiPass.getLifts(), "Lifts should be updated to 10.");
    }

    @Test
    void testPass_WithValidDateAndRemainingLifts() {
        // Arrange
        LocalDate today = LocalDate.now();
        LimitedLiftsSkiPass validSkiPass = new LimitedLiftsSkiPass(SkiPassType.LIFTS_LIMITED, today.minusDays(1), today.plusDays(1), 2);

        // Act & Assert
        assertTrue(validSkiPass.pass(), "Pass should be allowed with valid date and remaining lifts.");
        assertTrue(validSkiPass.pass(), "Second pass should also be allowed.");
        assertFalse(validSkiPass.pass(), "Pass should be denied when no lifts are left.");
    }
}
