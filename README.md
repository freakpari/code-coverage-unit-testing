# Software Testing Practice: Order Price Calculator

This repository contains a set of unit tests for a **Java Order Price Calculator** implemented as part of a **Software Testing course**. The tests cover various scenarios including valid orders, discounts, coupons, and invalid input handling.

---

## Features

- **100% Test Coverage**: All possible branches,instructions, and exception scenarios are covered.
- **Parameterized Tests**: Tests are optimized using JUnit 5 `@ParameterizedTest` and `@MethodSource` for concise and maintainable code.
- **Coupon & Discount Handling**: Tests include scenarios for multiple coupon codes (SAVE20, ELITE, FREESHIP) and item-based discounts.

---

## Running the Tests

To run the tests and generate a coverage report, run this command and see the result in index.html:

```bash
mvn clean verify
