package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainTest {

 @Test
    void throwExceptionForNullList(){
     assertThrows(IllegalArgumentException.class, () ->
       Main.calculateOrderPrice(null,null)
     );
 }
    @Test
    void throwExceptionForEmptyList(){
        assertThrows(IllegalArgumentException.class, () ->
                Main.calculateOrderPrice(Collections.emptyList(),"")
        );
    }
    @Test
    void negativePriceThrowsException(){
     List <Double> prices = Arrays.asList(10.0,-5.0,15.0);
     assertThrows(IllegalArgumentException.class, () ->
             Main.calculateOrderPrice(prices,"")
     );
    }
    @Test
    void nullPriceThrowsException(){
        List <Double> prices = Arrays.asList(10.0,null,15.0);
        assertThrows(IllegalArgumentException.class, () ->
                Main.calculateOrderPrice(prices,"")
        );
    }
    @Test
    void validPricesNoDiscountNoCoupon(){
        List <Double> prices = Arrays.asList(10.0,20.0,30.0);
        double result = Main.calculateOrderPrice(prices,null);
        assertEquals(60.0,result,0.001);
    }
    @Test
    void validPricesNoDiscountٍEmptyCoupon(){
        List <Double> prices = Arrays.asList(10.0,20.0,30.0);
        double result = Main.calculateOrderPrice(prices,"");
        assertEquals(60.0,result,0.001);
    }
    @Test
    void discountForTenItems(){
     List <Double> prices =Collections.nCopies(10,10.0);
     double result = Main.calculateOrderPrice(prices,"");
     assertEquals(90.0,result,0.001);
    }
    @Test
    void discountForTwentyItems(){
        List <Double> prices =Collections.nCopies(20,10.0);
        double result = Main.calculateOrderPrice(prices,"");
        assertEquals(170.0,result,0.001);
    }
    @Test
    void couponSave20Applied(){
     List <Double> prices = Arrays.asList(100.0,100.0);
     double result = Main.calculateOrderPrice(prices,"SAVE20");
     assertEquals(160.0,result,0.001);
    }
    @Test
    void couponEliteAbove1000() {

        List<Double> prices = Collections.nCopies(11, 100.0);
        double result = Main.calculateOrderPrice(prices, "ELITE");
        assertEquals(990.0, result, 0.001);
    }

    @Test
    void couponEliteWhenAbove1000WithoutQuantityDiscount() {
        List<Double> prices = Arrays.asList(250.0, 250.0, 250.0, 250.0, 250.0);
        double result = Main.calculateOrderPrice(prices, "ELITE");
        assertEquals(1150.0, result, 0.001);
    }
    @Test
    void couponFreeshipUnder50() {
        List<Double> prices = Arrays.asList(20.0, 20.0);
        double result = Main.calculateOrderPrice(prices, "FREESHIP");
        assertEquals(55.0, result, 0.001);
    }

    @Test
    void couponFreeShipAbove50() {
        List<Double> prices = Arrays.asList(30.0, 30.0);
        double result = Main.calculateOrderPrice(prices, "FREESHIP");
        assertEquals(60.0, result, 0.001);
    }

    @Test
    void unknownCouponCode() {
        List<Double> prices = Arrays.asList(50.0, 50.0);
        double result = Main.calculateOrderPrice(prices, "INVALIDCODE");
        assertEquals(100.0, result, 0.001);
    }
}
