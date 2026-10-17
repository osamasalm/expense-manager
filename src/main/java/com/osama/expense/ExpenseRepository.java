package com.osama.expense;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository {

    Expense add(Expense expense);

    Optional<Expense> findById(int id);

    List<Expense> findAll();

    Optional<Expense> deleteById(int id);

    public Expense update(Expense expense);

}
