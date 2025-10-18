package com.example;

import java.util.List;

public class Main {

    private Main() {
        // کانستراکتور باید خالی باشد
    }



    public static double calculateOrderPrice(List<Double> itemPrices, String couponCode) {
        if (itemPrices == null || itemPrices.isEmpty()) {
            throw new IllegalArgumentException("Item list cannot be null or empty.");
        }

        double totalPrice = 0;
        // 1. استفاده از حلقه برای محاسبه مجموع قیمت اولیه
        for (Double price : itemPrices) {
            if (price == null || price <= 0) {
                throw new IllegalArgumentException("All item prices must be positive.");
            }
            totalPrice += price;
        }

        int quantity = itemPrices.size();

        // 2. تخفیف
        if (quantity >= 20) {
            totalPrice *= 0.85; // تخفیف 15% برای ۲۰ آیتم یا بیشتر
        } else if (quantity >= 10) {
            totalPrice *= 0.90; // تخفیف 10% برای ۱۰ تا ۱۹ آیتم
        }

        // 3. کدهای تخفیف
        if (couponCode != null && !couponCode.isEmpty()) {
            switch (couponCode) {
                case "SAVE20":
                    totalPrice *= 0.80; // ۲۰٪ تخفیف
                    break;
                case "ELITE":
                    // اگر قیمت بعد از تخفیف تعدادی هنوز بالای ۱۰۰۰ باشد
                    if (totalPrice > 1000) {
                        totalPrice -= 100;
                    }
                    break;
                case "FREESHIP":
                    // اگر قیمت زیر ۵۰ باشد هزینه ارسال اضافه می‌شود، در غیر این صورت رایگان است
                    if (totalPrice < 50) {
                        totalPrice += 15;
                    }
                    break;
            }
        }
        
        return totalPrice;
    }
}