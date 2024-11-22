package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SeasonSkiPassTest {

    private SeasonSkiPass seasonSkiPass;

    @BeforeEach
    void setUp() {
        // Initialize SeasonSkiPass with a valid date range
        seasonSkiPass = new SeasonSkiPass(SkiPassType.SEASON, LocalDate.now(), LocalDate.now().plusMonths(3));
    }

    @Test
    void testPass_WithinSeason() {
        // Act
        boolean result = seasonSkiPass.pass();

        // Assert
        assertTrue(result, "Pass should be allowed when the current date is within the valid season date range.");
    }

    @Test
    void testPass_OutsideSeason() {
        // Arrange
        SeasonSkiPass expiredSkiPass = new SeasonSkiPass(SkiPassType.SEASON, LocalDate.now().minusMonths(6), LocalDate.now().minusMonths(3));

        // Act
        boolean result = expiredSkiPass.pass();

        // Assert
        assertFalse(result, "Pass should be denied when the current date is outside the valid season date range.");
    }

    @Test
    void testPass_BeforeSeasonStart() {
        // Arrange
        SeasonSkiPass futureSkiPass = new SeasonSkiPass(SkiPassType.SEASON, LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(4));

        // Act
        boolean result = futureSkiPass.pass();

        // Assert
        assertFalse(result, "Pass should be denied if the current date is before the season start date.");
    }

    @Test
    void testPass_OnSeasonStartDate() {
        // Arrange
        LocalDate today = LocalDate.now();
        SeasonSkiPass startingTodayPass = new SeasonSkiPass(SkiPassType.SEASON, today, today.plusMonths(3));

        // Act
        boolean result = startingTodayPass.pass();

        // Assert
        assertTrue(result, "Pass should be allowed if the current date is the season start date.");
    }

    @Test
    void testPass_OnSeasonEndDate() {
        // Arrange
        LocalDate today = LocalDate.now();
        SeasonSkiPass endingTodayPass = new SeasonSkiPass(SkiPassType.SEASON, today.minusMonths(3), today);

        // Act
        boolean result = endingTodayPass.pass();

        // Assert
        assertTrue(result, "Pass should be allowed if the current date is the season end date.");
    }
}
