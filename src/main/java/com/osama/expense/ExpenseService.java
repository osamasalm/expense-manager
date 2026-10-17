package com.osama.expense;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository repository;


    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    public Expense addExpense(String name,
                              BigDecimal amount,
                              LocalDate date,
                              int quantity,
                              String place) {

        validate(name, amount, date, quantity);
        if (place == null) place = "";
        return repository.add(new Expense(name, amount, date, quantity, place));
    }

    public Expense getExpense (int id) {
        return repository.findById(id).orElseThrow(
                () -> new ExpenseNotFoundException("Expense Not Found!")
        );
    }

    public List<Expense> listExpenses() {
        return repository.findAll();
    }

    public Expense deleteExpense (int id) {
        return repository.deleteById(id).orElseThrow(
                () -> new ExpenseNotFoundException("Expense Not Found!")
        );
    }

    public BigDecimal findTotalExpense() {

        BigDecimal total = BigDecimal.ZERO;

        for (Expense expense : listExpenses()) {
            total = total.add(expense.getAmount());
        }

        return total;
    }

    public Expense updateExpense(int id, String name, BigDecimal amount,
                                 LocalDate date, int quantity, String place) {
        // 1. validate the new values
        validate(name, amount, date, quantity);
        // 2. find the existing expense (this should throw if the id doesn't exist)
        Expense expense = getExpense(id);
        // 3. set the new values on it, using the setters
        expense.setName(name);
        expense.setAmount(amount);
        expense.setDate(date);
        expense.setQuantity(quantity);
        expense.setPlace(place);
        // 4. save it through the repository and return the result
        return repository.update(expense);
    }

    public Expense patchExpense(int id, String name, BigDecimal amount,
                                LocalDate date, Integer quantity, String place) {
        // 1. find the existing expense (throws if the id doesn't exist)
        Expense expense = getExpense(id);

        // 2. work out the final value of each field:
        //    the request's value if it is not null, otherwise the expense's current value
        name = name == null ? expense.getName() : name;
        amount = amount == null ? expense.getAmount() : amount;
        date = date == null ? expense.getDate() : date;
        quantity = quantity == null ? expense.getQuantity() : quantity;
        place = place == null ? expense.getPlace() : place;

        // 3. validate the final values with your existing validate(...) method
        validate(name,
                amount,
                date,
                quantity);

        // 4. set the final values on the expense
        expense.setName(name);
        expense.setAmount(amount);
        expense.setDate(date);
        expense.setQuantity(quantity);
        expense.setPlace(place);

        // 5. save it with repository.update(...) and return the result
        return repository.update(expense);
    }

    private void validate(String name, BigDecimal amount, LocalDate date, int quantity) {
        // place can be empty or null not important information
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name Can Not Be Empty!");
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Amount Must Be Positive!");
        if (date == null) throw new IllegalArgumentException("Date Must Exist!");
        if (quantity <= 0) throw new IllegalArgumentException("Quantity Must Be Positive!");
    }

}
