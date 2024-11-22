package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class StreamEncryptorTest {
    private static final char ENCRYPTION_KEY = 'Y';
    private StreamEncryptor encryptor;

    @BeforeEach
    void setUp() {
        encryptor = new StreamEncryptor(ENCRYPTION_KEY);
    }

    @Test
    void testResourceBundleInitializationForEnglish() {
        // Проверяем загрузку английского ресурса
        ResourceBundle messages = ResourceBundle.getBundle("location/messages_en", Locale.ENGLISH);
        assertEquals("Enter encryption key:", messages.getString("enter_key"));
    }
}
