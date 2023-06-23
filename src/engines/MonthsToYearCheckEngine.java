package engines;

import model.MonthlyReport;
import model.YearTransaction;
import model.YearlyReport;

import java.util.HashMap;
import java.util.ArrayList;

public class MonthsToYearCheckEngine {

    public void monthsToYearCheck(HashMap<String, MonthlyReport> monthsData, YearlyReport yearReport) {

        if (monthsData == null) {
            System.out.println("месяный отчет не считан");
        }

        if (yearReport == null) {
            System.out.println("Годовой отчет не считан");
        }

        //Подсчитать суммы доходов и расходов по каждому из месяцев.
        //MonthlyReportEngine calculateMonthsIncomeSum
        //MonthlyReportEngine calculateMonthsExpenseSum

        //Сверить полученные суммы с суммой доходов и расходов в отчёте по году.

        //При обнаружении несоответствия программа должна вывести месяц, где оно обнаружено.
        //Если несоответствий не обнаружено, приложение должно вывести только информацию об успешном завершении операции.
    }
    
    public void getTotalPerMonth(YearlyReport yearReport) { // считает доходы и расходы по каждому месяцу

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

}