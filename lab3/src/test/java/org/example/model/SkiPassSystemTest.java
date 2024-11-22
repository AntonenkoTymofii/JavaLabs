package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SkiPassSystemTest {

    private SkiPassSystem skiPassSystem;

    @BeforeEach
    void setUp() {
        skiPassSystem = new SkiPassSystem();
    }

    @Test
    void testRegistrateSkiPass_LimitedLiftsSkiPass() {
        // Arrange
        SkiPassType type = SkiPassType.LIFTS_LIMITED;
        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = LocalDate.now().plusDays(7);
        int lifts = 5;

        // Act
        SkiPass skiPass = skiPassSystem.registrateSkiPass(type, fromDate, toDate, lifts);

        // Assert
        assertNotNull(skiPass, "SkiPass should not be null.");
        assertTrue(skiPass instanceof LimitedLiftsSkiPass, "SkiPass should be an instance of LimitedLiftsSkiPass.");
        assertEquals(lifts, ((LimitedLiftsSkiPass) skiPass).getLifts(), "The number of lifts should be initialized correctly.");
        assertEquals(type, skiPass.getType(), "SkiPass type should match the provided type.");
    }

    @Test
    void testRegistrateSkiPass_SeasonSkiPass() {
        // Arrange
        SkiPassType type = SkiPassType.SEASON;
        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = LocalDate.now().plusMonths(3);

        // Act
        SkiPass skiPass = skiPassSystem.registrateSkiPass(type, fromDate, toDate, 0);

        // Assert
        assertNotNull(skiPass, "SkiPass should not be null.");
        assertTrue(skiPass instanceof SeasonSkiPass, "SkiPass should be an instance of SeasonSkiPass.");
        assertEquals(type, skiPass.getType(), "SkiPass type should match the provided type.");
    }

    @Test
    void testBlockSkiPass() {
        // Arrange
        SkiPassType type = SkiPassType.SEASON;
        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = LocalDate.now().plusMonths(3);

        SkiPass skiPass = skiPassSystem.registrateSkiPass(type, fromDate, toDate, 0);
        UUID skiPassId = skiPass.getId();

        // Act
        skiPassSystem.blockSkiPass(skiPassId);
        SkiPass retrievedSkiPass = skiPassSystem.getSkiPass(skiPassId);

        // Assert
        assertNotNull(retrievedSkiPass, "SkiPass should not be null.");
        assertTrue(retrievedSkiPass.isBlocked(), "SkiPass should be blocked.");
    }

    @Test
    void testGetSkiPass_ValidUUID() {
        // Arrange
        SkiPassType type = SkiPassType.SEASON;
        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = LocalDate.now().plusMonths(3);

        SkiPass skiPass = skiPassSystem.registrateSkiPass(type, fromDate, toDate, 0);
        UUID skiPassId = skiPass.getId();

        // Act
        SkiPass retrievedSkiPass = skiPassSystem.getSkiPass(skiPassId);

        // Assert
        assertNotNull(retrievedSkiPass, "SkiPass should not be null.");
        assertEquals(skiPassId, retrievedSkiPass.getId(), "The retrieved SkiPass ID should match the requested ID.");
    }

    @Test
    void testGetSkiPass_InvalidUUID() {
        // Act
        SkiPass skiPass = skiPassSystem.getSkiPass(UUID.randomUUID());

        // Assert
        assertNull(skiPass, "SkiPass should be null for an invalid UUID.");
    }
}
