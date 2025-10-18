package com.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class MainTest {

    @Test
    void testThrowsExceptionForNullOrEmptyList() {
        assertThrows(IllegalArgumentException.class, () -> Main.calculateOrderPrice(null, null));
        assertThrows(IllegalArgumentException.class, () -> Main.calculateOrderPrice(Collections.emptyList(), null));
    }

    static List<List<Double>> invalidPriceLists() {
        return Arrays.asList(
                //negative price ThrowsException
                Arrays.asList(10.0, -5.0, 15.0),
                //null price ThrowsException
                Arrays.asList(10.0, null, 15.0),
                //zero price ThrowsException
                Arrays.asList(10.0, 0.0, 15.0)
        );
    }
    @ParameterizedTest
    @MethodSource("invalidPriceLists")
    void testInvalidPricesThrowException(List<Double> prices) {
        assertThrows(IllegalArgumentException.class, () -> Main.calculateOrderPrice(prices, ""));
    }

    static List<Object[]> validOrders() {
        return Arrays.asList(new Object[][] {

                //sum for valid prices
                { Arrays.asList(10.0, 20.0, 30.0), "", 60.0 },
                //discount for 10 & 20 items
                { Collections.nCopies(10, 10.0), "", 90.0 },
                { Collections.nCopies(20, 10.0), "", 170.0 },
                //discount coupon codes
                { Arrays.asList(100.0, 100.0), "SAVE20", 160.0 },
                { Arrays.asList(500.0, 670.0), "ELITE", 1070.0 },
                { Arrays.asList(400.0, 500.0), "ELITE", 900.0 },
                { Arrays.asList(20.0, 20.0), "FREESHIP", 55.0 },
                { Arrays.asList(50.0, 20.0), "FREESHIP", 70.0 },
                //coupon code is empty & null
                { Arrays.asList(10.0, 20.0), null, 30.0 },
                { Arrays.asList(10.0, 20.0), "", 30.0 },
                //coupon code unknown default
                { Arrays.asList(100.0, 50.0), "INVALID", 150.0 }
        });
    }
    @ParameterizedTest
    @MethodSource("validOrders")
    void testCalculateOrderPrice(List<Double> prices, String coupon, double expected) {
        assertEquals(expected, Main.calculateOrderPrice(prices, coupon), 0.001);
    }
}