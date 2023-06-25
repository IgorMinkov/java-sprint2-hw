package engines;

import fileReader.MyFileReader;
import model.YearTransaction;
import model.YearlyReport;

import java.util.ArrayList;

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
            System.out.println("Годовой отчет не считан.");
            System.out.println("Сначала считайте его командой 2");
            return;
        }
        calculateProfit(yearReport);
        calculateAverage(yearReport);

    }

    public void calculateProfit(YearlyReport yearReport) { // считает прибыль по каждому месяцу

        if (yearReport == null) {
            System.out.println("годовой отчет пустой");
            return;
        }

        System.out.println("Данные за 2021 год:");
        for (int i = 1; i <= 3; i++) {

        int lineExpense = 0;
        int expenseNumber = 0;
        int lineIncome = 0;
        int incomeNumber = 0;
        int monthProfit = 0;

        for (YearTransaction line : yearReport.yearsData) {
            if (line.month == i && line.isExpense) {
                lineExpense = line.amount;
                expenseNumber = line.month;
            }
            if (line.month == i && !line.isExpense) {
                lineIncome = line.amount;
                incomeNumber = line.month;
            }
        }
            if (expenseNumber == incomeNumber) {
                monthProfit = lineIncome - lineExpense;
            }
            String monthName = getMonthName(i);
            System.out.println("Прибыль за " + monthName + ": " + monthProfit);
        }

    }

    public String getMonthName (int i) { // Получить имя месяца для вывода статистики
        String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        return months[i - 1];
    }

    public void calculateAverage(YearlyReport yearReport) { // средний доход и средний расход за год

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
        for (int expense : expenses) {
            expenseSum += expense;
        }
        int averageExpense = expenseSum / expenses.size();
        System.out.println("средний расход за год: " + averageExpense);

        int incomeSum = 0;
        for (int income : incomes) {
            incomeSum += income;
        }
        int averageIncome = incomeSum / incomes.size();
        System.out.println("средний доход за год: " + averageIncome);
    }

    public int calculateYearExpenseSum (YearlyReport yearlyReport, int month) {

        if (yearlyReport == null) {
            System.out.println("годовой отчет пустой");
            return 0;
        }

        int expenseSum = 0;
        for (YearTransaction line : yearlyReport.yearsData) {
            if (line.month == month && line.isExpense) {
                int lineExpense = line.amount;
                expenseSum += lineExpense;
            }
        }
        return expenseSum;
    }

    public int calculateYearIncomeSum (YearlyReport yearlyReport, int month) {

        if (yearlyReport == null) {
            System.out.println("годовой отчет пустой");
            return 0;
        }

        int incomeSum = 0;
        for (YearTransaction line : yearlyReport.yearsData) {
            if (line.month == month && !line.isExpense) {
                int lineIncome = line.amount;
                incomeSum += lineIncome;
            }
        }
        return incomeSum;
    }

}