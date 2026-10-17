package com.osama.expense;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExpenseServiceTest {

    ExpenseRepository repository;
    ExpenseService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryExpenseRepository();
        service = new ExpenseService(repository);
    }

    @Test
    void addExpense_blankName_throws() {

        // 1. Arrange: create a repository and a service

        // 2. Act + Assert, together:
        //    assertThrows(SomeException.class, () -> /* call addExpense with a blank name */);

        assertThrows(IllegalArgumentException.class, () -> {
            service.addExpense("      ",
                    new BigDecimal("20.0"),
                    LocalDate.of(2026, 10, 1),
                    5,
                    "Store");
        });
    }

    @Test
    void getExpense_idNotFound_throws() {
        assertThrows(ExpenseNotFoundException.class, () -> {
           service.getExpense(1);
        });
    }

    @Test
    void findTotalExpense_twoExpenses_returnsSum() {
        service.addExpense("Potato",
                new BigDecimal("20.0"),
                LocalDate.of(2026, 10, 1),
                5,
                "Store");

        service.addExpense("Tomato",
                new BigDecimal("50.0"),
                LocalDate.of(2026, 10, 1),
                8,
                "Store");
        assertEquals(0, new BigDecimal("70").compareTo(service.findTotalExpense()));
    }

    @Test
    void getExpense_idFound_getExpense() {
        service.addExpense("Potato",
                new BigDecimal("20.0"),
                LocalDate.of(2026, 10, 1),
                5,
                "Store");

        assertEquals(1, service.getExpense(1).getId());
    }
}
