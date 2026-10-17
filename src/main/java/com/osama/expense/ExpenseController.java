package com.osama.expense;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpenseController {

    private final ExpenseService service;

    public ExpenseController (ExpenseService service) {
        this.service = service;
    }

    @GetMapping("/expenses")
    public List<Expense> listExpenses () {
        return service.listExpenses();
    }

    @PostMapping("/expenses")
    public ResponseEntity<Expense> addExpense(@RequestBody ExpenseRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addExpense(
                request.name(),
                request.amount(),
                request.date(),
                request.quantity(),
                request.place()
        ));
    }

    @GetMapping("/expenses/{id}")
    public Expense getExpense(@PathVariable int id) {
        return service.getExpense(id);
    }

    @DeleteMapping("/expenses/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable int id) {
        service.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/expenses/{id}")
    public Expense updateExpense(@PathVariable int id, @RequestBody ExpenseRequest request) {
        return service.updateExpense(id, request.name(),
                request.amount(),
                request.date(),
                request.quantity(),
                request.place());
    }

    @PatchMapping("/expenses/{id}")
    public Expense patchExpense(@PathVariable int id, @RequestBody UpdateExpenseRequest request) {
        return service.patchExpense(id, request.name(),
                request.amount(),
                request.date(),
                request.quantity(),
                request.place());
    }


}
