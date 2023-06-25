package model;

public class MonthTransaction { // класс для строки списка месячных отчетов

    public String name;
    public boolean isExpense;
    public int quantity;
    public int unitPrice;

    public MonthTransaction (String name, Boolean isExpense, Integer quantity, Integer unitPrice) {
        this.name = name;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

}
