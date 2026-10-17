package com.osama.expense;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryExpenseRepository implements ExpenseRepository {

    private final AtomicInteger nextId;
    private final Map<Integer, Expense> expenses;

    public InMemoryExpenseRepository() {
        nextId = new AtomicInteger(1);
        expenses = new TreeMap<>();
    }

    @Override
    public Expense add(Expense expense) {

        expense.setId(nextId.getAndIncrement());
        expenses.put(expense.getId(), expense);

        return expense;
    }

    @Override
    public Optional<Expense> findById(int id) {
        return Optional.ofNullable(expenses.get(id));
    }

    @Override
    public List<Expense> findAll() {
        return new ArrayList<>(expenses.values());
    }

    @Override
    public Optional<Expense> deleteById(int id) {
        return Optional.ofNullable(expenses.remove(id));
    }

    @Override
    public Expense update(Expense expense) {
        expenses.put(expense.getId(), expense);
        return expense;
    }
}
