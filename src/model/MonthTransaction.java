package model;

public class MonthTransaction { // класс для каждой строки списка месячных отчетов

    public String name;
    public boolean isExpense;
    public int quantity;
    public int PriceOfOne;

    public MonthTransaction (String name, Boolean isExpense, Integer quantity, Integer PriceOfOne) {
        this.name = name;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.PriceOfOne = PriceOfOne;
    }

}
