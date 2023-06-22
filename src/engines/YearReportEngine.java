package engines;

import fileReader.MyFileReader;
import model.MonthTransaction;
import model.YearTransaction;
import model.YearlyReport;

import java.util.ArrayList;

public class YearReportEngine {
    public MyFileReader fileReader = new MyFileReader();
    public boolean isYearReport = false;

    public void readYearReport() {
        String filename = "y.2021.csv";
        String delimiter = ",";
        ArrayList<String> strings = fileReader.readFileContents(filename);
        if (strings.isEmpty()) {
            System.out.println("Ошибка -пустой список при чтении годового отчета!");
        }
        YearlyReport yearlyReport = new YearlyReport();
        for (int j = 1; j < strings.size(); j++) {
            String line = strings.get(j);
            String[] lineContents = line.split(delimiter);

            boolean isExpense = Boolean.parseBoolean(lineContents[2]);
            Integer month = Integer.parseInt(lineContents[0]);
            Integer amount = Integer.parseInt(lineContents[1]);
            YearTransaction yearTransaction = new YearTransaction(month, amount);
            if (isExpense) {
                yearlyReport.expenses.add(yearTransaction);
            } else {
                yearlyReport.incomes.add(yearTransaction);
            }
            yearlyReport.yearsData.put("Расходы", yearlyReport.expenses);
            yearlyReport.yearsData.put("Доходы", yearlyReport.incomes);
        }
        isYearReport = true;
        System.out.println("годовой отчет успешно считан");
    }


    public void calculateProfit() { // считает прибыль по каждому месяцу

        //проверить на считывание. если не считано, предложить считать.

        //System.out.println("Данные за 2021 год:");
        // int profitPerMonth;
        //int expense;
        //for (int i = 0; i < incomes.size(); i++) { // тонкое место - цикл ограничен длинной списка доходов
        //  YearTransaction income = model.YearTransaction.incomes.get(i);
        // expense = model.YearTransaction.expenses.get(i);
        // profitPerMonth = income - expense;
        // String monthName = getMonthName(i);
        //System.out.println("Прибыль за " + monthName + ": " + profitPerMonth);
        //}
    }

    public String getMonthName (int i) {       // Получить имя месяца для вывода статистики
        String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        return months[i - 1];
    }

    public void calculateAverage() { // средний доход за все операции
        // средний расход за все операции я бы тоже зашил в calculateAverage()

        //int incomeSum = 0;
        //int averageIncome;
        //for (int i = 0; i < incomes.size(); i++) {
        //incomeSum += model.YearTransaction.incomes.get(i);
        //averageIncome = incomeSum / incomes.size();
    //}
    }

    public void printYearReport() {
        (MonthTransaction line : expenses) {
            System.out.println(line);
        }
    }

}