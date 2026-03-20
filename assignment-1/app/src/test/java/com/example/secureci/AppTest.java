package com.example.secureci;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

final class AppTest {
    @Test
    void sanity() {
        assertTrue(true);
    }

    @Test
    void stringLengthExample() {
        assertEquals(2, "ok".length());
    }
}

