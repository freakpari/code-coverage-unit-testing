package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collections;

public class MainTest {
    @Test
    void testThrowsExceptionForNullList() {
        assertThrows(IllegalArgumentException.class, () -> {
            Main.calculateOrderPrice(null, null);
        });
    }

    @Test
    void testThrowsExceptionForEmptyList() {
        assertThrows(IllegalArgumentException.class, () -> {
            Main.calculateOrderPrice(Collections.emptyList(), null);
        });
    }
}