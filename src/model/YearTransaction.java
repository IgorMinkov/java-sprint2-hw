package model;

public class YearTransaction { // класс для каждой строки списка годовых отчетов
    public int month;
    public int amount;

    public YearTransaction (Integer month, Integer amount) {
        this.month = month;
        this.amount = amount;

    }
}
