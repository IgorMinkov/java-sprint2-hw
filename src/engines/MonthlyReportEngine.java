package engines;

import model.MonthTransaction;
import model.MonthlyReport;
import fileReader.MyFileReader;

import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReportEngine {

    public MyFileReader fileReader = new MyFileReader();

    public HashMap<String, MonthlyReport> readAllMonthlyReports() {

        String filePrefix = "";
        String delimiter = ",";
        HashMap<String,MonthlyReport> monthlyReports = new HashMap<>();

        for (int i = 1; i<=12; i++) {
            String filename = filePrefix + "m.2021" + getMonthIndex(i) + ".csv";
            ArrayList<String> strings = fileReader.readFileContents(filename);
            if (strings.isEmpty()) {
                continue;
            }
            MonthlyReport monthlyReport = new MonthlyReport();
            for (int j = 1; j < strings.size(); j++) {
                String line = strings.get(j);
                String[] lineContents = line.split (delimiter);

                MonthTransaction monthTransaction = getMonthTransaction(lineContents);
                monthlyReport.monthData.add(monthTransaction);
            }
            String monthName = getMonthName(i);
            monthlyReports.put(monthName, monthlyReport);

        }
        System.out.println("месячные отчеты успешно считаны");
        return monthlyReports;
    }

    private static MonthTransaction getMonthTransaction(String[] lineContents) {
        String name = lineContents[0];
        Boolean isExpense = Boolean.parseBoolean(lineContents[1]);
        Integer quantity = Integer.parseInt(lineContents[2]);
        Integer PriceOfOne = Integer.parseInt(lineContents[3]);
        MonthTransaction monthTransaction = new MonthTransaction(name, isExpense, quantity, PriceOfOne);
        return monthTransaction;
    }

    public String getMonthName (int i) {       // Получить имя месяца для вывода статистики
        String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        return months[i - 1];
    }
    public String getMonthIndex (int i) {       // Получить номер месяца для считывания месячных отчетов
        String[] months = {"01", "02", "03", "04", "05", "06",
                "07", "08", "09", "10", "11", "12"};
        return months[i - 1];
    }



    public void getMonthsStatistic (HashMap<String, MonthlyReport> monthsData) {
        // верно отдает, сумму на считает в выводе

        if (monthsData == null) {
            System.out.println("месячные отчеты не считаны.");
            System.out.println("Сначала считайте их командой 1");
            return;
        }

        for (String monthName : monthsData.keySet()) {
            int maxIncome = 0;
            String topProduct = null;
            int topProductSum = 0;
            int maxExpense = 0;
            String lowProduct = null;
            int lowProductSum = 0;
            System.out.println("Данные за " + monthName + ":");
            for (MonthTransaction line : monthsData.get(monthName).monthData) {
                if (line.isExpense) {
                    if (lowProduct == null) {
                        lowProduct = line.name;
                        continue;
                    }
                    if (maxExpense < line.quantity * line.PriceOfOne) {
                        maxExpense = line.quantity * line.PriceOfOne;
                        lowProduct = line.name;
                        lowProductSum = line.PriceOfOne;
                    }
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
            System.out.println("Самый прибыльный товар: " + topProduct + ", продано на сумму: " + topProductSum);
            System.out.println("Самая большая трата: " + lowProduct + ", потрачено: " + lowProductSum);
        }

    }

    public int calculateMonthsIncomeSum (MonthlyReport monthlyReport) {

        if (monthlyReport == null) {
            System.out.println("месячный отчет пустой");
            return 0;
        }

            int monthIncomeSum = 0;
            for (MonthTransaction line : monthlyReport.monthData) {
                if (!line.isExpense) {
                    int lineIncome = line.quantity * line.PriceOfOne;
                    monthIncomeSum += lineIncome;
                }
            }
            return monthIncomeSum;

    }
    public int calculateMonthsExpenseSum (MonthlyReport monthlyReport) {

        if (monthlyReport == null) {
            System.out.println("месячный отчет пустой");
            return 0;
        }

            int monthExpenseSum = 0;
            for (MonthTransaction line : monthlyReport.monthData) {
                if (line.isExpense) {
                    int lineIncome = line.quantity * line.PriceOfOne;
                    monthExpenseSum += lineIncome;
                }
            }
            return monthExpenseSum;

    }

}
