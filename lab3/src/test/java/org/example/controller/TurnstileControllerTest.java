package org.example.controller;

import org.example.model.SkiPass;
import org.example.model.SkiPassSystem;
import org.example.view.TurnstileView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

class TurnstileControllerTest {

    private SkiPassSystem skiPassSystem;
    private TurnstileView turnstileView;
    private TurnstileController turnstileController;

    @BeforeEach
    void setUp() {
        skiPassSystem = mock(SkiPassSystem.class);
        turnstileView = mock(TurnstileView.class);
        turnstileController = new TurnstileController(skiPassSystem, turnstileView);
    }

    @Test
    void testCheckSkiPass_PassAllowed() {
        // Arrange
        UUID skiPassId = UUID.randomUUID();
        SkiPass skiPass = mock(SkiPass.class);

        when(skiPassSystem.getSkiPass(skiPassId)).thenReturn(skiPass);
        when(skiPass.pass()).thenReturn(true);

        // Act
        turnstileController.checkSkiPass(skiPassId);

        // Assert
        verify(skiPassSystem).getSkiPass(skiPassId);
        verify(skiPass).pass();
        verify(turnstileView).showPassAllowed();
    }

    @Test
    void testCheckSkiPass_PassDenied() {
        // Arrange
        UUID skiPassId = UUID.randomUUID();
        SkiPass skiPass = mock(SkiPass.class);

        when(skiPassSystem.getSkiPass(skiPassId)).thenReturn(skiPass);
        when(skiPass.pass()).thenReturn(false);

        // Act
        turnstileController.checkSkiPass(skiPassId);

        // Assert
        verify(skiPassSystem).getSkiPass(skiPassId);
        verify(skiPass).pass();
        verify(turnstileView).showPassDenied();
    }

    @Test
    void testCheckSkiPass_PassDenied_NullSkiPass() {
        // Arrange
        UUID skiPassId = UUID.randomUUID();

        when(skiPassSystem.getSkiPass(skiPassId)).thenReturn(null);

        // Act
        turnstileController.checkSkiPass(skiPassId);

        // Assert
        verify(skiPassSystem).getSkiPass(skiPassId);
        verify(turnstileView).showPassDenied();
    }

    @Test
    void testShowStatistics() {
        // Arrange
        UUID skiPassId = UUID.randomUUID();
        SkiPass skiPass = mock(SkiPass.class);

        when(skiPassSystem.getSkiPass(skiPassId)).thenReturn(skiPass);
        when(skiPass.pass()).thenReturn(true);

        // Simulate passing and denial
        turnstileController.checkSkiPass(skiPassId);
        when(skiPass.pass()).thenReturn(false);
        turnstileController.checkSkiPass(skiPassId);

        // Act
        turnstileController.showStatistics();

        // Assert
        verify(turnstileView).showStatistics(1, 1);
    }
}
