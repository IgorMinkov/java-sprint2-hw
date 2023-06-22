package engines;

import model.MonthTransaction;
import model.MonthlyReport;
import fileReader.MyFileReader;

import java.util.ArrayList;

public class MonthlyReportEngine {

    public MyFileReader fileReader = new MyFileReader();
    public ArrayList<MonthTransaction> rows = new ArrayList<>();
    public boolean isMonthlyReport = false;

    public void readAllMonthlyReports() {

        String filePrefix = "";
        String delimiter = ",";

        for (int i = 1; i<=3; i++) {
            String filename = filePrefix + "m.20210" + i + ".csv";
            ArrayList<String> strings = fileReader.readFileContents(filename);
            if (strings.isEmpty()) {
                System.out.println("Ошибка -пустой список при чтении месячного отчета!");
            }
            MonthlyReport monthlyReport = new MonthlyReport();
            for (int j = 1; j < strings.size(); j++) {
                String line = strings.get(j);
                String[] lineContents = line.split (delimiter);

                String name = lineContents[0];
                Boolean isExpense = Boolean.parseBoolean(lineContents[1]);
                Integer quantity = Integer.parseInt(lineContents[2]);
                Integer PriceOfOne = Integer.parseInt(lineContents[3]);
                MonthTransaction monthTransaction = new MonthTransaction(name, isExpense, quantity, PriceOfOne);
                rows.add(monthTransaction);
            }
            String monthName = getMonthName(i);
            monthlyReport.monthsData.put(monthName, rows);

        }
        isMonthlyReport = true;
        System.out.println("месячные отчеты успешно считаны");
    }

    public String getMonthName (int i) {       // Получить имя месяца для вывода статистики
        String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        return months[i - 1];
    }

    public void getMonthsStatistic () { // getMaxExpense() и getMaxIncome() вместе, по отдельности не используются

        // проверить на считывание

        for (String monthName : monthsData.keySet()) {
            int maxIncome = 0;
            String topProduct = null;
            int topProductSum = 0;
            int maxExpense = 0;
            String lowProduct = null;
            int lowProductSum = 0;
            System.out.println("Данные за " + monthName + ":");
            for (MonthTransaction line : monthsData.get(monthName)) {
                if (line.isExpense) {
                    if (lowProduct == null) {
                        lowProduct = line.name;
                        continue;
                    }
                    if (maxExpense < line.quantity * line.PriceOfOne) {
                        maxExpense = line.quantity * line.PriceOfOne;
                        lowProduct = line.name;
                        lowProductSum = line.PriceOfOne;
                    } else {
                        if (topProduct == null) {
                            topProduct = line.name;
                            continue;
                        }
                        if (maxIncome < line.quantity * line.PriceOfOne) {
                            maxIncome = line.quantity * line.PriceOfOne;
                            topProduct = line.name;
                            topProductSum = line.PriceOfOne;
                        }
                    }
                }
            }
            System.out.println("Самый прибыльный товар: " + topProduct + ", продано на сумму: " + topProductSum);
            System.out.println("Самая большая трата: " + lowProduct + ", потрачено: " + lowProductSum);
        }

    }

    public void calculateMonthsIncomeSum () {

    }

    public void calculateMonthsExpenseSum () {

    }

}
