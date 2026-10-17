package com.osama.expense;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense {

    private int id;
    private String name;
    // amount is the total price for all quantity NOT the price of a single item.
    private BigDecimal amount;
    private LocalDate date;
    private int quantity;
    private String place;

    public Expense(String name, BigDecimal amount, LocalDate date, int quantity, String place) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.quantity = quantity;
        this.place = place;
    }

    public int getId() {
        return id;
}

    void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ". Name: " + name +
                " - Total Amount: " + amount +
                "$ for " + quantity +
                " of " + name +
                ". From " + place +
                " at date: " + date;
    }

}
