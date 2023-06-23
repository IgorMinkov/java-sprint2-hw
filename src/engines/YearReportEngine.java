package engines;

import fileReader.MyFileReader;
import model.YearTransaction;
import model.YearlyReport;

import java.util.ArrayList;
import java.util.HashMap;

public class YearReportEngine {
    public MyFileReader fileReader = new MyFileReader();

    public YearlyReport readYearReport() {
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

            Integer month = Integer.parseInt(lineContents[0]);
            Integer amount = Integer.parseInt(lineContents[1]);
            boolean isExpense = Boolean.parseBoolean(lineContents[2]);
            YearTransaction yearTransaction = new YearTransaction(month, amount, isExpense);
            yearlyReport.yearsData.add(yearTransaction);
        }
        System.out.println("годовой отчет успешно считан");
        return yearlyReport;
    }

    public void getYearStatistic(YearlyReport yearReport) {

        if (yearReport == null) {
            System.out.println("Годовой отчет не считан");
        }
        calculateProfit(yearReport);
        calculateAverage(yearReport);

    }

    public void calculateProfit(YearlyReport yearReport) { // считает прибыль по каждому месяцу

        HashMap<Integer, Integer> profitPerMonths = new HashMap<>();

        System.out.println("Данные за 2021 год:");
        for (int i = 0; i < yearReport.yearsData.size(); i++) {
            YearTransaction yearTransaction = yearReport.yearsData.get(i);
            Integer profitPerMonth = profitPerMonths.get(yearTransaction.amount);
            if (profitPerMonth == null) {
                profitPerMonth = 0;
            }
            if (yearTransaction.isExpense) {
                profitPerMonth -= yearTransaction.amount;
            } else {
                profitPerMonth += yearTransaction.amount;
            }
            profitPerMonths.put(yearTransaction.month, profitPerMonth);
        }

        for (Integer month : profitPerMonths.keySet()) {
            String monthName = getMonthName(month);
            System.out.println("Прибыль за " + monthName + ": " + profitPerMonths.get(month));
        }
    }

    public String getMonthName (int i) { // Получить имя месяца для вывода статистики
        String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        return months[i - 1];
    }

    public void calculateAverage(YearlyReport yearReport) { // средний доход и средний расход за все операции

        ArrayList<Integer> expenses = new ArrayList<>();
        ArrayList<Integer> incomes = new ArrayList<>();

        for (int i = 0; i < yearReport.yearsData.size(); i++) {
            YearTransaction yearTransaction = yearReport.yearsData.get(i);
            if (yearTransaction.isExpense) {
                expenses.add(yearTransaction.amount);
            } else {
                incomes.add(yearTransaction.amount);
            }
        }

        int expenseSum = 0;
        for (int i : expenses) {
            expenseSum += expenses.get(i);
        }
        int averageExpense = expenseSum / expenses.size();
        System.out.println("средний расход за месяц" + averageExpense);

        int incomeSum = 0;
        for (int i : incomes) {
            incomeSum += incomes.get(i);
        }
        int averageIncome = incomeSum / incomes.size();
        System.out.println("средний доход за месяц" + averageIncome);
    }

}