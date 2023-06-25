package model;

public class YearTransaction { // класс для строки списка годовых отчетов

    public int month;
    public int amount;
    public boolean isExpense;

    public YearTransaction (Integer month, int amount, boolean isExpense) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }

}
