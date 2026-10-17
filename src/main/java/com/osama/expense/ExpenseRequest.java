package com.osama.expense;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseRequest(String name, BigDecimal amount, LocalDate date, int quantity, String place) {
}
