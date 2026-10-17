package com.osama.expense;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateExpenseRequest(String name, BigDecimal amount, LocalDate date, Integer quantity, String place) {
}

